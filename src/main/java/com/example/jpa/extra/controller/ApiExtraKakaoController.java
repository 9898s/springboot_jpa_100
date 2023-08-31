package com.example.jpa.extra.controller;

import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.extra.model.KakaoGptInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApiExtraKakaoController {

    @PostMapping("/api/extra/kakao/gpt")
    public ResponseEntity<?> translate(@RequestBody KakaoGptInput kakaoGptInput) {
        String restApiKey = "ba1e3177bbca7a803a568f54caf034ec";
        String url = "https://api.kakaobrain.com/v1/inference/kogpt/generation";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "KakaoAK " + restApiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", kakaoGptInput.getText());
        requestBody.put("max_tokens", 120);
        requestBody.put("n", 2);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        return ResponseResult.success(responseEntity.getBody());
    }

}
