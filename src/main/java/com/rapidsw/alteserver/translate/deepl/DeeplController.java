package com.rapidsw.alteserver.translate.deepl;

import com.rapidsw.alteserver.translate.deepl.dto.TranslationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/translate")
public class DeeplController {

    private final DeeplServ deeplServ;

    public DeeplController(DeeplServ deeplServ) {
        this.deeplServ = deeplServ;
    }

    @PostMapping("/deepl")
    public Map<String, Object> toDeepL(@RequestBody TranslationRequest request){
        System.out.println(request.getText());
        return deeplServ.translate(request);
    }
}
