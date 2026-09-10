package com.qtrong.plantcare.dto.request;

import com.qtrong.plantcare.dto.response.AiPredictionResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryRequest {
    private String imageUrl;
    private AiPredictionResult predictionResult;
}