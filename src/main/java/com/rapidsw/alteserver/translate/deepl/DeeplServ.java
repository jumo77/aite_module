package com.rapidsw.alteserver.translate.deepl;

import com.rapidsw.alteserver.translate.deepl.dto.TranslationRequest;

import java.util.Map;

public interface DeeplServ {
    Map<String, Object> translate(TranslationRequest request);
}
