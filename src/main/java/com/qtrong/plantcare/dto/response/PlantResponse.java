package com.qtrong.plantcare.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.qtrong.plantcare.enums.PlantType;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlantResponse {
    private String name;
    private PlantType species;
    private String imageUrl;
    private Date plantedAt;
    private int wateringIntervalDays;
    private Date lastWateredAt;
    private Date createdAt;
}
