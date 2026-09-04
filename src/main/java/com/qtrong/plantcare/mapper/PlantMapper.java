package com.qtrong.plantcare.mapper;

import com.qtrong.plantcare.dto.request.PlantCreationRequest;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PlantMapper {
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
}
