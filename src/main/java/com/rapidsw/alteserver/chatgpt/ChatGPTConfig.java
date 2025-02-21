package com.rapidsw.alteserver.chatgpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ChatGPTConfig {

    @Value("${openai.secret-key}")
    private String key;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
