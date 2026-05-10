package me.rentapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenCageResponse {
    private List<Result> results;

    @Getter
    @Setter
    public static class Result {
        private OpenCageComponents components;
    }
}
