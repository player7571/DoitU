package com.example.DoitU.controller;

import com.example.DoitU.dto.DiaryDto;
import com.example.DoitU.dto.EmojiDto;
import com.example.DoitU.service.CalenderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doitu/api/calender")
public class CalenderController {

    private final CalenderService calenderService;

    @PostMapping("/emoji")
    public ResponseEntity<?> postEmoji(@RequestBody EmojiDto emojiDto, HttpSession httpSession) {
        return calenderService.setEmoji(emojiDto, httpSession);
    }

    @PostMapping("/diary")
    public ResponseEntity<?> postDiary(@RequestBody DiaryDto diaryDto, HttpSession httpSession) {
        return calenderService.setDiary(diaryDto, httpSession);
    }

    @GetMapping("/{year}/{month}")
    public ResponseEntity<?> getCalenderInfo(@PathVariable int year, @PathVariable int month, HttpSession httpSession) {
        return calenderService.getInfo(year, month, httpSession);
    }

    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<?> getCalenderDetailInfo(@PathVariable int year, @PathVariable int month, @PathVariable int day, HttpSession httpSession) {
        return calenderService.getDetailInfo(year, month, day, httpSession);
    }

}
