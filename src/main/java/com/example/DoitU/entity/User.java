package com.example.DoitU.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id @Column
    private String userId;

    @Column(nullable = false, unique = true)
    private String password;
}
