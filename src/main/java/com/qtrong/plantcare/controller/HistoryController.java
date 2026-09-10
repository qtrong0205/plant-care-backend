package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.HistoryRequest;
import com.qtrong.plantcare.entity.AiPredictionResult;
import com.qtrong.plantcare.entity.History;
import com.qtrong.plantcare.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping
    public History addHistory(
            @RequestBody HistoryRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return historyService.addHistory(
                request.getImageUrl(),
                request.getPredictionResult(),
                jwt,
                null
        );
    }

    @PostMapping("/{plant_id}")
    public History addHistory(
            @PathVariable("plant_id") String plantId,
            @RequestBody HistoryRequest request,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return historyService.addHistory(
                request.getImageUrl(),
                request.getPredictionResult(),
                jwt,
                plantId
        );
    }
}
