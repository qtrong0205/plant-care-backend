package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.PlantResponse;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService plantService;

    @PostMapping
    public ApiResponse<PlantResponse> createPlant(
            @Valid @RequestPart("plant") PlantCreationRequest request,
            @RequestPart("image") MultipartFile image,
            @AuthenticationPrincipal Jwt jwt
            ) throws IOException {
        return plantService.createPlant(request, image, jwt);
    }

    @GetMapping("/{plant-id}")
    public ApiResponse<PlantResponse> getPlant(
            @PathVariable("plant-id") String plantId,
            @AuthenticationPrincipal Jwt jwt
    ) throws IOException {
        return plantService.getPlant(plantId, jwt);
    }
}
