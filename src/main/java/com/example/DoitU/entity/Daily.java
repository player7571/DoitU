package com.example.DoitU.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Daily {

    @Id
    private String day;

    private String diary;

    private String emoji;

}
