package com.rapidsw.alteserver.translate.google;

import com.google.cloud.translate.Translate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoogleImpl implements GoogleServ {

    private final Translate translate;

    public GoogleImpl(Translate translate) {
        this.translate = translate;
    }

    @Override
    public String translate(String startLang, String destLang, String text) {
        return translate.translate(
                text,
                Translate.TranslateOption.sourceLanguage(startLang),
                Translate.TranslateOption.targetLanguage(destLang),
                Translate.TranslateOption.format("text"))
                .getTranslatedText();
    }
}