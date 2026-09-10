package com.qtrong.plantcare.controller;


import com.qtrong.plantcare.dto.response.AiPredictionResult;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
    private final AiService aiService;

    @GetMapping
    public String testHealth(){
        return aiService.testHealth();
    }

    @PostMapping("/classify")
    public ApiResponse<AiPredictionResult> predict(
            @RequestPart("image") MultipartFile image
    ){
        return aiService.predict(image);
    }
}
