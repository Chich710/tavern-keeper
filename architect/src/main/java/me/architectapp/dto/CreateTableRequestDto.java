package me.architectapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateTableRequestDto {
    private String tableName;
    private List<ColumnDto> columns;
}
