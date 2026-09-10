package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.response.AiPredictionResult;
import com.qtrong.plantcare.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestClient restClient;

    public String testHealth() {
        return restClient.get()
                .uri("")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<String>() {});
    }

    public ApiResponse<AiPredictionResult> predict(MultipartFile image) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", image.getResource());

        return ApiResponse.<AiPredictionResult>builder()
                .code(200)
                .result(
                        restClient.post()
                                .uri("/classify/")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .body(builder.build())
                                .retrieve()
                                .body(AiPredictionResult.class)
                )
                .build();
    }
}
