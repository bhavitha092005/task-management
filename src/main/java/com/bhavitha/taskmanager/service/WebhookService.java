package com.bhavitha.taskmanager.service;

import com.bhavitha.taskmanager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${webhook.url}")
    private String webhookUrl;

    public void sendTaskCompleted(Task task) {

        int maxRetries = 3;
        long delay = 1000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {

            try {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> body = new HashMap<>();
                body.put("taskId", task.getId());
                body.put("title", task.getTitle());
                body.put("userId", task.getUserId());
                body.put("completedAt", LocalDateTime.now());

                HttpEntity<Map<String, Object>> request =
                        new HttpEntity<>(body, headers);

                restTemplate.postForEntity(
                        webhookUrl,
                        request,
                        String.class
                );

                System.out.println("Webhook sent successfully");
                return;

            } catch (Exception e) {

                System.out.println("Webhook failed attempt " + attempt);

                if (attempt == maxRetries) {
                    System.out.println("Webhook failed permanently");
                    return;
                }

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {
                }

                delay *= 2;
            }
        }
    }
}