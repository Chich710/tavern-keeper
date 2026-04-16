package me.architectapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateTableRequestDto {
    private String tableName;
    private List<ColumnDto> columns;
}
