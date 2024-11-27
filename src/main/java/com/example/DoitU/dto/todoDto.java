package com.example.DoitU.dto;

import com.example.DoitU.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class todoDto {

    private Long id;
    private String title;
    private String content;
    private String color;
    private boolean done;

    public todoDto(Todo todo) {

        this.id = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.color = todo.getColor();
        this.done = todo.getDone();

    }
}
