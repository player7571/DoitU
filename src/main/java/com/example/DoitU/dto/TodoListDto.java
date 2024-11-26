package com.example.DoitU.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TodoListDto {

    private int statusCode;
    private String msg;
    List<routineDto> routineDto = new ArrayList<>();
    List<todoDto> todoDto = new ArrayList<>();

}
