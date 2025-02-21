package com.rapidsw.alteserver.translate.google;

import org.springframework.stereotype.Service;

@Service
public interface GoogleServ {
    String translate(String startLang, String endLang, String text);
}
