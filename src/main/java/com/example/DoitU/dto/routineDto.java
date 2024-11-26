package com.example.DoitU.dto;


import com.example.DoitU.entity.Routine;
import com.example.DoitU.entity.Week;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class routineDto {

    private boolean sun;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thr;
    private boolean fri;
    private boolean sat;

    private Long id;
    private String title;
    private String content;
    private String color;

    public routineDto(Routine routine){

        List<Week> list = routine.getWeekList();
        for (Week week : list) {
            switch (week.toString()) {
                case "SUN":
                    sun = true;
                    break;
                case "MON":
                    mon = true;
                    break;
                case "TUE":
                    tue = true;
                    break;
                case "WED":
                    wed = true;
                    break;
                case "THR":
                    thr = true;
                    break;
                case "FRI":
                    fri = true;
                    break;
                case "SAT":
                    sat = true;
                    break;

            }
        }

        this.id = routine.getRoutineId();
        this.title = routine.getTitle();
        this.content = routine.getContent();
        this.color = routine.getColor();
    }

}
