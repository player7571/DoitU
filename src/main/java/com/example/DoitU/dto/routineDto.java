package com.example.DoitU.dto;


import com.example.DoitU.entity.Routine;
import com.example.DoitU.entity.Week;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private List<String> dayOfWeek;

    private Long id;
    private String title;
    private String content;
    private String color;

    public routineDto(Routine routine){

        List<Week> list = routine.getWeekList();
        List<String> dayOfWeek = new ArrayList<>();
        for (Week week : list) {
            switch (week.toString()) {
                case "SUN":
                    sun = true;
                    dayOfWeek.add("sun");
                    break;
                case "MON":
                    dayOfWeek.add("mon");
                    mon = true;
                    break;
                case "TUE":
                    dayOfWeek.add("tue");
                    tue = true;
                    break;
                case "WED":
                    dayOfWeek.add("wed");
                    wed = true;
                    break;
                case "THR":
                    dayOfWeek.add("thr");
                    thr = true;
                    break;
                case "FRI":
                    dayOfWeek.add("fri");
                    fri = true;
                    break;
                case "SAT":
                    dayOfWeek.add("sat");
                    sat = true;
                    break;

            }
        }
        this.dayOfWeek = dayOfWeek;
        this.id = routine.getRoutineId();
        this.title = routine.getTitle();
        this.content = routine.getContent();
        this.color = routine.getColor();
    }

}
