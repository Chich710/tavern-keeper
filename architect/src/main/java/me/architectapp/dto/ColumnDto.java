package me.architectapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnDto {
    private String name;
    private String type;
    private String constraints;
}
