package com.example.google.domain;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KakaoOauth implements SocialOauth {
    private final String KAKAO_SNS_BASE_URL = "https://kauth.kakao.com/oauth/authorize";
    private final String KAKAO_SNS_CLIENT_ID = "b4dad0c3fd74414e64580f182c1e5df9";
    private final String KAKAO_SNS_CALLBACK_URL = "http://localhost:8080/auth/kakao/callback";
    private final String KAKAO_SNS_TOKEN_BASE_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_SNS_CLIENT_SECRET = "3r9403RCBoYumFfkKz2JAgASwDdDavyQ";

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", KAKAO_SNS_CLIENT_ID);
        params.put("redirect_uri", KAKAO_SNS_CALLBACK_URL);
        params.put("response_type", "code");
        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return KAKAO_SNS_BASE_URL + "?" + parameterString;
    }

    @Override
    public String requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_SNS_CLIENT_ID);
        params.add("client_secret", KAKAO_SNS_CLIENT_SECRET);
        params.add("redirect_uri", KAKAO_SNS_CALLBACK_URL);
        params.add("code", code);
//        params.put("grant_type", "authorization_code");
//        params.put("client_id", KAKAO_SNS_CLIENT_ID);
//        params.put("client_secret", KAKAO_SNS_CLIENT_SECRET);
//        params.put("redirect_uri", KAKAO_SNS_CALLBACK_URL);
//        params.put("code", code);
        System.out.println(code);


        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(KAKAO_SNS_TOKEN_BASE_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return "카카오로그인 요청 처리 실패";
    }
}