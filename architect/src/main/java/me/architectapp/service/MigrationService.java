package me.architectapp.service;

import me.architectapp.dto.AlterColumnRequestDto;
import me.architectapp.dto.CreateTableRequestDto;
import me.architectapp.dto.DropTableRequestDto;

public interface MigrationService {
    String createTableMigration(CreateTableRequestDto request);
    String alterColumnMigration(AlterColumnRequestDto request);
    String dropTableMigration(DropTableRequestDto request);
}
