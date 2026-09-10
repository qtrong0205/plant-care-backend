package com.qtrong.plantcare.service;

import com.nimbusds.jose.util.Resource;
import com.qtrong.plantcare.dto.response.AiPredictionResult;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AiService {

    private final RestClient restClient;
    private final HistoryService historyService;
    private final SupabaseStorageService storageService;
    private final PlantRepository plantRepository;

    public String testHealth() {
        return restClient.get()
                .uri("")
                .retrieve()
                .body(new org.springframework.core.ParameterizedTypeReference<String>() {});
    }

    public ApiResponse<AiPredictionResult> predict(
            MultipartFile image,
            Jwt jwt,
            String plantId
    ) throws IOException {

        String imageUrl = null;

        if (plantId != null) {
            imageUrl = plantRepository.getImageUrlByPlantId(plantId);
        }

        if (image == null && imageUrl == null) {
            throw new IllegalArgumentException("Image is required");
        }

        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        try {
            if (image != null) {
                imageUrl = storageService.uploadImage(image);
                builder.part("file", image.getResource());
            } else {
                byte[] imageBytes = restClient.get()
                        .uri(imageUrl)
                        .retrieve()
                        .body(byte[].class);

                ByteArrayResource imageResource = new ByteArrayResource(imageBytes) {
                    @Override
                    public String getFilename() {
                        return "image.jpg";
                    }
                };

                builder.part("file", imageResource);
            }

            var predictionResult = restClient.post()
                    .uri("/classify/")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(builder.build())
                    .retrieve()
                    .body(AiPredictionResult.class);

            historyService.addHistory(
                    imageUrl,
                    predictionResult,
                    jwt,
                    plantId
            );

            return ApiResponse.<AiPredictionResult>builder()
                    .code(200)
                    .result(predictionResult)
                    .build();

        } catch (Exception e) {
            if (image != null && imageUrl != null) {
                try {
                    storageService.deleteImage(imageUrl);
                } catch (Exception deleteException) {
                }
            }
            throw e;
        }
    }
}