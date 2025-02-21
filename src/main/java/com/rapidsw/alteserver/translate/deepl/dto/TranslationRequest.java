package com.rapidsw.alteserver.translate.deepl.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslationRequest {
    private List<String> text;
    private String target_lang;
}