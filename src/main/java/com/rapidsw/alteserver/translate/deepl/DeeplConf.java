package com.rapidsw.alteserver.translate.deepl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
public class DeeplConf {

    @Value("${deepl.secret-key}")
    private String key;

    @Bean
    HttpHeaders httpHeadersDeepl() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + key);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
