package com.example.DoitU.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private User user;

}
