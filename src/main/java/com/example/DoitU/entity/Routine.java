package com.example.DoitU.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comments;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Routine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineId;

    @Column
    private String title;
    private String content;
    @ElementCollection
    private List<Week> weekList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "userId",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private User user;

    private String color;

    @CreationTimestamp
    private LocalDateTime createdTime;
}