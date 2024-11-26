package com.example.DoitU.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestTodoDto {

    private String title;
    private String content;

    private boolean sun;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thr;
    private boolean fri;
    private boolean sat;

    private String color;

}
