package com.example.jpa.extra.controller;

import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.common.properties.NaverAppProperties;
import com.example.jpa.extra.model.NaverTranslateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
public class ApiExtraNaverController {
    private final NaverAppProperties naverAppProperties;


//    @GetMapping("/api/extra/naver/translate")
//    public ResponseEntity<?> translate(@RequestBody NaverTranslateInput naverTranslateInput) {
////        curl "https://openapi.naver.com/v1/papago/n2mt" \
////        -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" \
////        -H "X-Naver-Client-Id: sqkyEIBHze2J8A54vyGc" \
////        -H "X-Naver-Client-Secret: 3cmn5jfWpo" \
////        -d "source=ko&target=en&text=만나서 반갑습니다." -v
//
//        String clientId = "sqkyEIBHze2J8A54vyGc";
//        String clientSecret = "3cmn5jfWpo";
//
//        String url = "https://openapi.naver.com/v1/papago/n2mt";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("X-Naver-Client-Id", clientId);
//        headers.add("X-Naver-Client-Secret", clientSecret);
//
//        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//        requestBody.add("source", "ko");
//        requestBody.add("target", "en");
//        requestBody.add("text", naverTranslateInput.getText());
//
//        HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
//
//        return ResponseResult.success(responseEntity.getBody());
//    }

    @GetMapping("/api/extra/naver/translate")
    public ResponseEntity<?> translate(@RequestBody NaverTranslateInput naverTranslateInput) {
//        curl "https://openapi.naver.com/v1/papago/n2mt" \
//        -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" \
//        -H "X-Naver-Client-Id: sqkyEIBHze2J8A54vyGc" \
//        -H "X-Naver-Client-Secret: 3cmn5jfWpo" \
//        -d "source=ko&target=en&text=만나서 반갑습니다." -v

        String clientId = naverAppProperties.getClientId();
        String clientSecret = naverAppProperties.getClientSecret();

        String url = "https://openapi.naver.com/v1/papago/n2mt";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("X-Naver-Client-Id", clientId);
        headers.add("X-Naver-Client-Secret", clientSecret);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("source", "ko");
        requestBody.add("target", "en");
        requestBody.add("text", naverTranslateInput.getText());

        HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        return ResponseResult.success(responseEntity.getBody());
    }
}
