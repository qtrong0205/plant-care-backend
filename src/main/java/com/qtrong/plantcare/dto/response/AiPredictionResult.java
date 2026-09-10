package com.qtrong.plantcare.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qtrong.plantcare.entity.TopKAiResults;
import com.qtrong.plantcare.enums.AiPredictionStatus;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AiPredictionResult {

    @JsonProperty("predicted_label")
    private String predictedLabel;

    private Float confidence;

    @JsonProperty("top_k")
    private List<TopKAiResults> topKAiResults;

    private AiPredictionStatus status;
}
