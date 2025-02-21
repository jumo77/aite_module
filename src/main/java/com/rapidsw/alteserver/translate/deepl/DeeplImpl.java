package com.rapidsw.alteserver.translate.deepl;

import com.rapidsw.alteserver.Literals;
import com.rapidsw.alteserver.translate.deepl.dto.TranslationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DeeplImpl implements DeeplServ {

    private final DeeplConf deeplConf;

    public DeeplImpl(DeeplConf deeplConf) {
        this.deeplConf = deeplConf;
    }

    @Value("${deepl.translate-url}")
    private String url;

    @Override
    public Map<String, Object> translate(TranslationRequest request) {
        HttpHeaders headers = deeplConf.httpHeadersDeepl();

        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity<TranslationRequest> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = new RestTemplate()
                .exchange(url, HttpMethod.POST, requestEntity, String.class);
        return Literals.getStringObjectMap(response);
    }
}
