package com.qtrong.plantcare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    @Value("${spring.signer-key}")
    private String signerKey;
    @Value("${ai.service.url}")
    private String aiServiceUrl;

    @Bean
    public String signerKey() {
        return signerKey;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public RestClient aiRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl(aiServiceUrl)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
