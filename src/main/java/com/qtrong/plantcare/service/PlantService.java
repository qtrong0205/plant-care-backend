package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.PlantResponse;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.mapper.PlantMapper;
import com.qtrong.plantcare.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;
    private final SupabaseStorageService supabaseStorageService;
    private final PlantMapper plantMapper;
    private final JwtService jwtService;

    public ApiResponse<PlantResponse> createPlant(
            PlantCreationRequest request,
            MultipartFile image,
            Jwt jwt
    ) throws IOException {
        String imageUrl = supabaseStorageService.uploadImage(image);

        var user = jwtService.extractUser(jwt);

        var plant = plantMapper.toPlant(request, user, imageUrl);
        plantRepository.save(plant);

        return ApiResponse.<PlantResponse>builder()
                .code(201)
                .result(plantMapper.toPlantResponse(plant))
                .build();
    }
}
