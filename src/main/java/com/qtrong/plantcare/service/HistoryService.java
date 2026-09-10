package com.qtrong.plantcare.service;

import com.qtrong.plantcare.entity.AiPredictionResult;
import com.qtrong.plantcare.entity.History;
import com.qtrong.plantcare.entity.Plant;
import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.repository.HistoryRepository;
import com.qtrong.plantcare.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final JwtService jwtService;
    private final PlantRepository plantRepository;

    public History addHistory(
            String imageUrl,
            AiPredictionResult predictionResult,
            Jwt jwt,
            String plantId
    ) {
        User user = jwtService.extractUser(jwt);

        Plant plant = null;

        if (plantId != null) {
            plant = plantRepository
                    .findByPlantIdAndUser(plantId, user)
                    .orElseThrow(() -> new AppException(ErrorCode.PLANT_NOT_EXISTED));
        }

        History history = new History();

        history.setImageUrl(imageUrl);
        history.setPredictedLabel(predictionResult.getPredictedLabel());
        history.setConfidence(predictionResult.getConfidence());
        history.setTopKAiResults(predictionResult.getTopKAiResults());
        history.setStatus(predictionResult.getStatus());
        history.setCreateAt(new Date());

        history.setUser(user);
        history.setPlant(plant);

        return historyRepository.save(history);
    }
}