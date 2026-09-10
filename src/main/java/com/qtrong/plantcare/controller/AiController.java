package com.qtrong.plantcare.controller;


import com.qtrong.plantcare.dto.response.AiPredictionResult;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiController {
    private final AiService aiService;

    @GetMapping
    public String testHealth(){
        return aiService.testHealth();
    }

    @PostMapping(value = {"/classify", "/classify/{plantId}"})
    public ApiResponse<AiPredictionResult> predict(
            @RequestPart(value = "image", required = false) MultipartFile image,
            @PathVariable(value = "plantId", required = false) String plantId,
            @AuthenticationPrincipal Jwt jwt
            ) throws IOException {
        return aiService.predict(image, jwt, plantId);
    }
}
