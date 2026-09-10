package com.qtrong.plantcare.entity;

import com.qtrong.plantcare.enums.AiPredictionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AiPredictionResult {
    private String predictedLabel;
    private Float confidence;
    private List<TopKAiResults> topKAiResults;
    private AiPredictionStatus status;
}
