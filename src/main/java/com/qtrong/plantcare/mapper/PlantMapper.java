package com.qtrong.plantcare.mapper;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.dto.response.PlantResponse;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlantMapper {
    private final UserMapper userMapper;

    public Plant toPlant(PlantCreationRequest request, User user, String imageUrl){
        var plant = new Plant();
        plant.setName(request.getName());
        plant.setSpecies(request.getSpecies());
        plant.setPlantedAt(request.getPlantedAt());
        plant.setCreatedAt(request.getCreatedAt());
        plant.setLastWateredAt(request.getLastWateredAt());
        plant.setWateringIntervalDays(request.getWateringIntervalDays());
        plant.setUser(user);
        plant.setImageUrl(imageUrl);
        return plant;
    }

    public PlantResponse toPlantResponse(Plant plant){
        var plantResponse = new PlantResponse();
        plantResponse.setPlantId(plant.getPlantId());
        plantResponse.setName(plant.getName());
        plantResponse.setSpecies(plant.getSpecies());
        plantResponse.setStatus(plant.getStatus());
        plantResponse.setDisease(plant.getDisease());
        plantResponse.setPlantedAt(plant.getPlantedAt());
        plantResponse.setCreatedAt(plant.getCreatedAt());
        plantResponse.setLastWateredAt(plant.getLastWateredAt());
        plantResponse.setWateringIntervalDays(plant.getWateringIntervalDays());
        plantResponse.setImageUrl(plant.getImageUrl());
        return plantResponse;
    }
}
