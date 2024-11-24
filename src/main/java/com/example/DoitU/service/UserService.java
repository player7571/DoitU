package com.example.DoitU.service;


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

        Map<String, Object> response = new HashMap<>();

        try {

            // 사용자명이 이미 존재하는지 확인
            if (userRepository.findByUserId(userDto.getUserId()).isPresent()) {
                response.put("statusCode", 33);
                response.put("msg", "이미 사용중인 아이디입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            // 새 사용자 생성
            User newUser = new User();
            newUser.setUserId(userDto.getUserId());
            newUser.setPassword(passwordEncoder.encode(userDto.getPassword())); // 비밀번호 암호화
            userRepository.save(newUser);

        } catch (Exception e) {
            response.put("statusCode", 400);
            response.put("msg", "회원가입 오류!");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("statusCode", 200);
        response.put("msg", "회원가입 성공!");

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> userSignIn(UserDto userDto, HttpSession session){

        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(userDto.getPassword())) {
            response.put("statusCode", 25);
            response.put("msg", "잘못된 비밀번호입니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        session.setAttribute("user", user); // 세션에 사용자 정보 저장

        response.put("statusCode", 200);
        response.put("msg", "로그인 성공!");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> userSignOut(HttpSession session){

        session.invalidate(); // 세션 무효화

        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", 200);
        response.put("msg", "로그아웃 성공!");

        return ResponseEntity.ok(response);
    }
}
