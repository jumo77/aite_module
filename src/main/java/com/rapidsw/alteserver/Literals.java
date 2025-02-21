package com.rapidsw.alteserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Literals {
    public static String FILE_PATH = "/Users/jumokang/aite/files";

    public static Map<String, Object> getStringObjectMap(ResponseEntity<String> response) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            ObjectMapper om = new ObjectMapper();
            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            System.out.println("JsonMappingException :: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException :: " + e.getMessage());
        }
        return resultMap;
    }
}
