package com.qtrong.plantcare.controller;


import com.qtrong.plantcare.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
    private final AiService aiService;

    @GetMapping
    public String testHealth(){
        return aiService.testHealth();
    }
}
