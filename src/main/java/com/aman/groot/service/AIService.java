package com.aman.groot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${ai.openai.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateReply(String userMessage) {

        String url = "https://api.openai.com/v1/responses";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> body = Map.of(
                "model", "gpt-4o-mini",
                "input", List.of(
                        Map.of(
                                "role", "user",
                                "content", List.of(
                                        Map.of("type", "text", "text", userMessage)
                                )
                        )
                )
        );

        try {
            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(body, headers);

            Map<String, Object> response =
                    restTemplate.postForObject(url, request, Map.class);

            List<Map<String, Object>> output =
                    (List<Map<String, Object>>) response.get("output");

            Map<String, Object> content =
                    (Map<String, Object>) output.get(0).get("content");

            List<Map<String, Object>> parts =
                    (List<Map<String, Object>>) content.get("parts");

            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI error, please try again.";
        }
    }
}
