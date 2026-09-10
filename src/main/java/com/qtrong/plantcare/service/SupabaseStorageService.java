package com.qtrong.plantcare.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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

        String fileName = file.getOriginalFilename();

        String extension = getFileExtension(fileName);

        MediaType mediaType = getMediaType(fileName);

        String uploadUrl = String.format(
                "%s/storage/v1/object/%s/%s",
                supabaseUrl,
                bucketName,
                UUID.randomUUID() + extension
        );

        restClient.post()
                .uri(uploadUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + supabaseKey)
                .header("apikey", supabaseKey)
                .contentType(mediaType)
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

    public void deleteImage(String imageUrl) {
        String prefix = String.format( "%s/storage/v1/object/public/%s/", supabaseUrl, bucketName );

        if (!imageUrl.startsWith(prefix)) {
            throw new IllegalArgumentException("Invalid Supabase image URL");
        }

        String filePath = imageUrl.substring(prefix.length());
        String deleteUrl = String.format( "%s/storage/v1/object/%s/%s", supabaseUrl, bucketName, filePath );

        restClient.delete()
                .uri(deleteUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + supabaseKey)
                .header("apikey", supabaseKey)
                .retrieve()
                .toBodilessEntity();
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }

    private MediaType getMediaType(String fileName) {
        String extension = getFileExtension(fileName).toLowerCase();

        return switch (extension) {
            case ".jpg", ".jpeg" -> MediaType.IMAGE_JPEG;
            case ".png" -> MediaType.IMAGE_PNG;
            case ".gif" -> MediaType.IMAGE_GIF;
            default -> throw new IllegalArgumentException("Unsupported image type");
        };
    }
}
