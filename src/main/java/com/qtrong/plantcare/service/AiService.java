package com.qtrong.plantcare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
}
