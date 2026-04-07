package me.architectapp.service;

import lombok.RequiredArgsConstructor;
import me.architectapp.dto.AlterColumnRequestDto;
import me.architectapp.dto.ColumnDto;
import me.architectapp.dto.CreateTableRequestDto;
import me.architectapp.dto.DropTableRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MigrationService {
    @Value("${migration.dir}")
    private String migrationDir;

    private Path getMigrationDir() {
        return Paths.get(migrationDir);
    }

    public String createTableMigration(CreateTableRequestDto request) {
        Integer nextVersion = getNextVersion();
        String fileName = String.format("V%03d__create_%s.sql", nextVersion, request.getTableName());
        String sql = buildCreateTableSql(request);
        Path filePath = getMigrationDir().resolve(fileName);
        try {
            Files.writeString(filePath, sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create migration file: " + fileName, e);
        }

        return fileName;
    }

    private Integer getNextVersion() {
        Path dir = getMigrationDir();
        try (Stream<Path> files = Files.list(dir)) {

            return files
                    .map(it -> it.getFileName().toString())
                    .filter(name -> name.matches("V\\d+__.*\\.sql"))
                    .map(name -> name.substring(1, name.indexOf("__")))
                    .mapToInt(Integer::parseInt)
                    .max()
                    .orElse(0) + 1;
        } catch (Exception e) {
            throw new RuntimeException("Failed to determine next migration version", e);
        }
    }

    public String alterColumnMigration(AlterColumnRequestDto request) {
        Integer nextVersion = getNextVersion();
        String fileName = String.format("V%03d__alter_%s.sql", nextVersion, request.getTableName());
        String sql = buildAlterColumnSql(request);
        Path filePath = getMigrationDir().resolve(fileName);

        try {
            Files.writeString(filePath, sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create migration file: " + fileName, e);
        }

        return fileName;
    }

    public String dropTableMigration(DropTableRequestDto request) {
        Integer nextVersion = getNextVersion();
        String fileName = String.format("V%03d__drop_%s.sql", nextVersion, request.getTableName());
        String sql = String.format("DROP TABLE IF EXISTS %s;\n", request.getTableName());
        Path filePath = getMigrationDir().resolve(fileName);

        try {
            Files.writeString(filePath, sql);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create migration file: " + fileName, e);
        }

        return fileName;
    }

    private String buildAlterColumnSql(AlterColumnRequestDto request) {
        StringBuilder strBuilder = new StringBuilder();
        String actualColumnName = request.getColumnName();

        if (request.getNewColumnName() != null && !request.getNewColumnName().isBlank()) {
            strBuilder.append(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;\n",
                    request.getTableName(), actualColumnName, request.getNewColumnName()));
            actualColumnName = request.getNewColumnName();
        }

        if (request.getNewType() != null && !request.getNewType().isBlank()) {
            strBuilder.append(String.format("ALTER TABLE %s ALTER COLUMN %s TYPE %s;\n",
                    request.getTableName(), actualColumnName, request.getNewType()));
        }

        if (request.getNewConstraints() != null && !request.getNewConstraints().isBlank()) {
            String constraints = request.getNewConstraints().trim().toUpperCase();
            if (constraints.equals("NULL") || constraints.equals("DROP NOT NULL")) {
                strBuilder.append(String.format("ALTER TABLE %s ALTER COLUMN %s DROP NOT NULL;\n",
                        request.getTableName(), actualColumnName));
            } else {
                strBuilder.append(String.format("ALTER TABLE %s ALTER COLUMN %s SET %s;\n",
                        request.getTableName(), actualColumnName, request.getNewConstraints()));
            }
        }

        return strBuilder.toString();
    }

    private String buildCreateTableSql(CreateTableRequestDto request) {
        String columns = request.getColumns().stream()
                .map(this::formatColumn)
                .collect(Collectors.joining(",\n    "));

        return String.format("""
                CREATE TABLE %s
                (
                    %s
                );
                """, request.getTableName(), columns);
    }

    private String formatColumn(ColumnDto column) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %s", column.getName(), column.getType()));
        if (column.getConstraints() != null && !column.getConstraints().isBlank()) {
            sb.append(" ").append(column.getConstraints());
        }
        return sb.toString();
    }
}
