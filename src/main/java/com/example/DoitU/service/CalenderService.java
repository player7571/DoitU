package com.example.DoitU.service;

import com.example.DoitU.dto.*;
import com.example.DoitU.entity.Daily;
import com.example.DoitU.entity.Routine;
import com.example.DoitU.entity.Todo;
import com.example.DoitU.repository.CalenderRepository;
import com.example.DoitU.repository.RoutineRepository;
import com.example.DoitU.repository.TodoRepository;
import com.example.DoitU.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalenderService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final RoutineRepository routineRepository;
    private final CalenderRepository calenderRepository;

    public ResponseEntity<?> getInfo(int year, int month, HttpSession session){

        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0, 0);
        Month startMonth = startDate.getMonth();

        List<CalenderDto> calenderDto = new ArrayList<>();

        while (startDate.getMonth()==startMonth){

            LocalDateTime endDate = startDate.plusDays(1).minusSeconds(1);
            List<Todo> todoList = todoRepository.findByUserAndCreatedTimeBetweenOrderByCreatedTimeDesc(userRepository.findByUserId((String) session.getAttribute("userId")).get(), startDate, endDate);
            List<todoDto> todoDto = new ArrayList<>();
            for (Todo todo : todoList) {
                com.example.DoitU.dto.todoDto dto = new todoDto(todo);
                todoDto.add(dto);
            }
            List<Routine> routineList = routineRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId((String) session.getAttribute("userId")).get());
            List<routineDto> routineDto = new ArrayList<>();
            for(Routine routine : routineList){
                com.example.DoitU.dto.routineDto dto = new routineDto(routine);
                routineDto.add(dto);
            }
            CalenderDto calDto = new CalenderDto();
            calDto.setTodoDto(todoDto);
            calDto.setRoutineDto(routineDto);
            calenderDto.add(calDto);
            Optional<Daily> daily = calenderRepository.findById(startDate.toLocalDate().toString());
            if(daily.isPresent()){
                if(daily.get().getEmoji()!=null){
                    calDto.setEmoji(daily.get().getEmoji());
                }
            }
            calDto.setDate(startDate.toLocalDate().toString());
            startDate = startDate.plusDays(1);
        }

        CalenderListDto calenderListDto = new CalenderListDto();
        calenderListDto.setStatusCode(200);
        calenderListDto.setMsg("불러오기 성공!");
        calenderListDto.setCalenderDto(calenderDto);
        return ResponseEntity.ok(calenderListDto);
    }

    public ResponseEntity<?> getDetailInfo(int year, int month, int day, HttpSession session){

        CalenderDetailDto calenderDetailDto = new CalenderDetailDto();
        LocalDateTime startDate = LocalDateTime.of(year, month, day, 0, 0, 0);
        LocalDateTime endDate = startDate.plusDays(1).minusSeconds(1);
        Optional<Daily> daily = calenderRepository.findByDayAndUser(startDate.toLocalDate().toString(),userRepository.findByUserId((String) session.getAttribute("userId")).get());
        System.out.println(startDate.toLocalDate().toString());//형식이 어케되는지 출력해봄
        if(daily.isPresent()){
            if(daily.get().getEmoji()!=null){
                calenderDetailDto.setEmoji(daily.get().getEmoji());
            }
            if(daily.get().getDiary()!=null){
                calenderDetailDto.setDiary(daily.get().getDiary());
            }
        }

        List<Todo> todoList = todoRepository.findByUserAndCreatedTimeBetweenOrderByCreatedTimeDesc(userRepository.findByUserId((String) session.getAttribute("userId")).get(), startDate, endDate);
        List<todoDto> todoDto = new ArrayList<>();
        for (Todo todo : todoList) {
            com.example.DoitU.dto.todoDto dto = new todoDto(todo);
            todoDto.add(dto);
        }
        List<Routine> routineList = routineRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId((String) session.getAttribute("userId")).get());
        List<routineDto> routineDto = new ArrayList<>();
        for(Routine routine : routineList){
            com.example.DoitU.dto.routineDto dto = new routineDto(routine);
            routineDto.add(dto);
        }

        calenderDetailDto.setStatusCode(200);
        calenderDetailDto.setMsg("불러오기 성공!");
        calenderDetailDto.setTodoDto(todoDto);
        calenderDetailDto.setRoutineDto(routineDto);

        return ResponseEntity.ok(calenderDetailDto);

    }

}