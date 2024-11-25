package com.example.DoitU.service;

import com.example.DoitU.dto.BasicResponse;
import com.example.DoitU.dto.UserDto;
import com.example.DoitU.entity.User;
import com.example.DoitU.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    final private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;

    public ResponseEntity<?> userSignUp(UserDto userDto) {

        try {
            // 사용자명이 이미 존재하는지 확인
            if (userRepository.findByUserId(userDto.getUserId()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new BasicResponse(33, "이미 사용중인 아이디입니다."));
            }

            // 새 사용자 생성
            User newUser = new User();
            newUser.setUserId(userDto.getUserId());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); // 비밀번호 암호화
            userRepository.save(newUser);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BasicResponse(400, "회원가입 오류!"));
        }

        return ResponseEntity.ok(new BasicResponse(200, "회원가입 성공!"));
    }

    public ResponseEntity<?> userSignIn(UserDto userDto, HttpSession session){

        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없음"));

        if (!user.getPassword().equals(userDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BasicResponse(25, "잘못된 비밀번호입니다."));
        }

        session.setAttribute("user", user); // 세션에 사용자 정보 저장

        return ResponseEntity.ok(new BasicResponse(200, "로그인 성공!"));
    }

    public ResponseEntity<?> userSignOut(HttpSession session){

        session.invalidate(); // 세션 무효화

        return ResponseEntity.ok(new BasicResponse(200, "로그아웃 성공!"));
    }
}
