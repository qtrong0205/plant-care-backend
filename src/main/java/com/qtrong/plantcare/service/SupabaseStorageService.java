package com.qtrong.plantcare.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${spring.supabase.url}")
    private String supabaseUrl;

    @Value("${spring.supabase.key}")
    private String supabaseKey;

    @Value("${spring.supabase.bucket}")
    private String bucketName;

    private final RestClient restClient = RestClient.create();

    public String uploadImage(MultipartFile file) throws IOException {

        String extension = getFileExtension(file.getOriginalFilename());

        String fileName =
                UUID.randomUUID() + extension;

        String uploadUrl = String.format(
                "%s/storage/v1/object/%s/%s",
                supabaseUrl,
                bucketName,
                fileName
        );

        restClient.post()
                .uri(uploadUrl)
                .header("Authorization", "Bearer " + supabaseKey)
                .header("apikey", supabaseKey)
                .contentType(
                        MediaType.parseMediaType(file.getContentType())
                )
                .body(file.getBytes())
                .retrieve()
                .toBodilessEntity();

        return String.format(
                "%s/storage/v1/object/public/%s/%s",
                supabaseUrl,
                bucketName,
                fileName
        );
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
