package me.architectapp.controller;

import lombok.RequiredArgsConstructor;
import me.architectapp.dto.AlterColumnRequestDto;
import me.architectapp.dto.CreateTableRequestDto;
import me.architectapp.dto.DropTableRequestDto;
import me.architectapp.service.MigrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migration")
@RequiredArgsConstructor
public class MigrationController {

    private final MigrationService migrationService;

    @PostMapping("/create")
    public String createTable(@RequestBody CreateTableRequestDto request) {
        String fileName = migrationService.createTableMigration(request);
        return "Migration created: " + fileName;
    }

    @PostMapping("/alter")
    public String alterColumn(@RequestBody AlterColumnRequestDto request) {
        String fileName = migrationService.alterColumnMigration(request);
        return "Migration created: " + fileName;
    }

    @PostMapping("/drop")
    public String dropTable(@RequestBody DropTableRequestDto request) {
        String fileName = migrationService.dropTableMigration(request);
        return "Migration created: " + fileName;
    }
}
