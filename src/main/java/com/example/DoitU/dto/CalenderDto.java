package com.example.DoitU.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CalenderDto {

    private String date;
    private String emoji;
    private String diary;
    private List<routineDto> routineDto = new ArrayList<>();
    private List<todoDto> todoDto = new ArrayList<>();

}
