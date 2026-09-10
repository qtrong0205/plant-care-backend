package com.qtrong.plantcare.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum AiPredictionStatus {
    OK,
    LOW_CONFIDENCE;

    @JsonCreator
    public static AiPredictionStatus fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
}
