package com.example.DoitU.controller;

import com.example.DoitU.dto.RequestTodoDto;
import com.example.DoitU.service.TodoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doitu/api/todoList")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<?> createTodoList(HttpSession session, @RequestBody RequestTodoDto requestTodoDto) {
        return todoService.createList(session, requestTodoDto);
    }

    @GetMapping("/ALL")
    public ResponseEntity<?> getAllTodolist(HttpSession session) {
        return todoService.getAllList(session);
    }

    @GetMapping("/WORKING")
    public ResponseEntity<?> getWorkingTodolist(HttpSession session) {
        return todoService.getList(false, session);
    }

    @GetMapping("/DONE")
    public ResponseEntity<?> getDoneTodolist(HttpSession session) {
        return todoService.getList(true, session);
    }


}
