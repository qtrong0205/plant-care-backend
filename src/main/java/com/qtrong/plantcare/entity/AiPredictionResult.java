package com.qtrong.plantcare.entity;

import com.qtrong.plantcare.enums.AiPredictionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AiPredictionResult {
    private String predictedLabel;
    private Float confidence;
    private TopKAiResults topKAiResults;
    private AiPredictionStatus status;
}
