package com.example.DoitU.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CalenderListDto {

    private int statusCode;
    private String msg;
    private List<CalenderDto> calenderDto = new ArrayList<>();
}
