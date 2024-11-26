package com.example.DoitU.service;

import com.example.DoitU.dto.*;
import com.example.DoitU.entity.Routine;
import com.example.DoitU.entity.Todo;
import com.example.DoitU.entity.Week;
import com.example.DoitU.repository.RoutineRepository;
import com.example.DoitU.repository.TodoRepository;
import com.example.DoitU.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final RoutineRepository routineRepository;

    public ResponseEntity<?> createList(HttpSession session, RequestTodoDto requestTodoDto){

        List<Boolean> list = new ArrayList<>();
        list.add(requestTodoDto.isSun());
        list.add(requestTodoDto.isMon());
        list.add(requestTodoDto.isTue());
        list.add(requestTodoDto.isWed());
        list.add(requestTodoDto.isThr());
        list.add(requestTodoDto.isFri());
        list.add(requestTodoDto.isSat());
        if(list.contains(true)){
            Routine routine = new Routine();
            routine.setUser(userRepository.findByUserId(session.getId()).get());
//            routine.setUser(userRepository.findByUserId("asd123").get());
            routine.setTitle(requestTodoDto.getTitle());
            routine.setContent(requestTodoDto.getContent());
            routine.setColor(requestTodoDto.getColor());
            List<Week> weekList = new ArrayList<>();
            if(requestTodoDto.isSun()) weekList.add(Week.SUN);
            if(requestTodoDto.isMon()) weekList.add(Week.MON);
            if(requestTodoDto.isTue()) weekList.add(Week.TUE);
            if(requestTodoDto.isWed()) weekList.add(Week.WED);
            if(requestTodoDto.isThr()) weekList.add(Week.THR);
            if(requestTodoDto.isFri()) weekList.add(Week.FRI);
            if(requestTodoDto.isSat()) weekList.add(Week.SAT);
            routine.setWeekList(weekList);
            routineRepository.save(routine);

        }
        else {
            Todo todo = new Todo();
            todo.setUser(userRepository.findByUserId(session.getId()).get());
//            todo.setUser(userRepository.findByUserId("asd123").get());
            todo.setTitle(requestTodoDto.getTitle());
            todo.setContent(requestTodoDto.getContent());
            todo.setColor(requestTodoDto.getColor());
            todo.setDone(false);
            todoRepository.save(todo);
        }

        return ResponseEntity.ok(new BasicResponse(200, "작성 완료"));

    }

    public ResponseEntity<?> getAllList(HttpSession session){

        List<Todo> todoList = todoRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId(session.getId()).get());
//        List<Todo> todoList = todoRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId("asd123").get());
        List<todoDto> todoDto = new ArrayList<>();
        for (Todo todo : todoList) {
            com.example.DoitU.dto.todoDto dto = new todoDto(todo);
            todoDto.add(dto);
        }

        List<Routine> routineList = routineRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId(session.getId()).get());
//        List<Routine> routineList = routineRepository.findByUserOrderByCreatedTimeDesc(userRepository.findByUserId("asd123").get());
        List<routineDto> routineDto = new ArrayList<>();
        for(Routine routine : routineList){
            com.example.DoitU.dto.routineDto dto = new routineDto(routine);
            routineDto.add(dto);
        }

        TodoListDto todoListDto = new TodoListDto();
        todoListDto.setStatusCode(200);
        todoListDto.setMsg("불러오기 성공!");
        todoListDto.setTodoDto(todoDto);
        todoListDto.setRoutineDto(routineDto);

        return ResponseEntity.ok(todoListDto);
    }

}
