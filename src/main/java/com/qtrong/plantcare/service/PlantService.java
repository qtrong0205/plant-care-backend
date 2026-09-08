package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.dto.request.PlantUpdateRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.PlantResponse;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.mapper.PlantMapper;
import com.qtrong.plantcare.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    public ApiResponse<PlantResponse> getPlant(
            String plantId,
            Jwt jwt
    ) {
        var user = jwtService.extractUser(jwt);

        var plant = plantRepository.findByPlantIdAndUser(plantId, user)
                .orElseThrow(() ->
                        new AppException(ErrorCode.PLANT_NOT_EXISTED)
                );

        return ApiResponse.<PlantResponse>builder()
                .code(200)
                .result(plantMapper.toPlantResponse(plant))
                .build();
    }

    public ApiResponse<List<PlantResponse>> getAllPlants(Jwt jwt) {
        var userId = jwtService.extractUserId(jwt);

        var plants = plantRepository.findAllByUser_UserId(userId);

        var result = plants.stream()
                .map(plantMapper::toPlantResponse)
                .toList();

        return ApiResponse.<List<PlantResponse>>builder()
                .code(200)
                .result(result)
                .build();
    }

    public ApiResponse<Void> watering(String plantId, Jwt jwt) {
        var user = jwtService.extractUser(jwt);

        var plant = plantRepository.findByPlantIdAndUser(plantId, user)
                .orElseThrow(() ->
                        new AppException(ErrorCode.PLANT_NOT_EXISTED)
                );

        plant.setLastWateredAt(new Date());
        plantRepository.save(plant);

        return ApiResponse.<Void>builder()
                .code(200)
                .message("Watered successfully")
                .build();
    }

    public ApiResponse<Void> delete(String plantId, Jwt jwt) {
        var user = jwtService.extractUser(jwt);

        var plant = plantRepository.findByPlantIdAndUser(plantId, user)
                .orElseThrow(() ->
                        new AppException(ErrorCode.PLANT_NOT_EXISTED)
                );

        plantRepository.delete(plant);

        return ApiResponse.<Void>builder()
                .code(200)
                .message("Plant deleted successfully")
                .build();
    }

    public ApiResponse<PlantResponse> update(
            String plantId,
            PlantUpdateRequest request,
            Jwt jwt
    ){
        var user = jwtService.extractUser(jwt);

        var plant = plantRepository.findByPlantIdAndUser(plantId, user)
                .orElseThrow(() ->
                        new AppException(ErrorCode.PLANT_NOT_EXISTED)
                );

        plantMapper.updatePlant(plant, request);

        plantRepository.save(plant);

        return ApiResponse.<PlantResponse>builder()
                .code(200)
                .result(plantMapper.toPlantResponse(plant))
                .build();
    }
}
