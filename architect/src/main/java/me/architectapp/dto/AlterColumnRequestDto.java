package me.architectapp.dto;

import lombok.Data;

@Data
public class AlterColumnRequestDto {
    private String tableName;
    private String columnName;
    private String newColumnName;
    private String newType;
    private String newConstraints;
}
