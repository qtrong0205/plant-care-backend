package com.qtrong.plantcare.dto.request;

import com.qtrong.plantcare.enums.PlantType;
import lombok.Getter;

import java.util.Date;

@Getter
public class PlantUpdateRequest {
    private String name;
    private Date plantedAt;
    private Integer wateringIntervalDays;
    private PlantType species;
}

