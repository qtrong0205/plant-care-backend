package com.qtrong.plantcare.dto.request;

import com.qtrong.plantcare.entity.PlantType;
import lombok.Getter;

import java.util.Date;

@Getter
public class PlantCreationRequest {
    private String name;
    private PlantType species;
    private Date plantedAt;
    private int wateringIntervalDays;
    private Date lastWateredAt;
    private Date createdAt;
}

