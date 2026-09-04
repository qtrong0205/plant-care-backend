package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.service.PlantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService plantService;

    @PostMapping
    public Plant createPlant(
            @Valid @RequestPart("plant") PlantCreationRequest request,
            @RequestPart("image") MultipartFile image,
            @AuthenticationPrincipal Jwt jwt
            ) throws IOException {
        return plantService.createPlant(request, image, jwt);
    }
}
