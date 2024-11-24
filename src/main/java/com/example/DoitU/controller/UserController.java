package com.example.DoitU.controller;

import com.example.DoitU.dto.UserDto;
import com.example.DoitU.entity.User;
import com.example.DoitU.repository.UserRepository;
import com.example.DoitU.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화를 위한 인코더

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return userService.userSignUp(userDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session) {
        return userService.userSignIn(userDto, session);
    }

    @GetMapping("/signout")
    public ResponseEntity<?> logout(HttpSession session) {
        return userService.userSignOut(session);
    }


}
