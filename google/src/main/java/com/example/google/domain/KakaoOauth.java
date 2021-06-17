package com.example.google.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
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
    private String to;


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

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(KAKAO_SNS_TOKEN_BASE_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper om = new ObjectMapper();

            Map<String, String> map=null;
            try {
                map = om.readValue(responseEntity.getBody(), Map.class);
            }
            catch (Exception e){
                e.printStackTrace();
            }


            return info(map);
        }
        return "카카오로그인 요청 처리 실패";
    }

    public String info(Map<String,String> map){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("property_keys","[\"kakao_account.email\"]");

        String token = "Bearer {"+map.get("access_token")+"}";

        to = token;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);

        System.out.println(token);

        String url ="https://kapi.kakao.com/v2/user/me";

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                url, //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class);

        ObjectMapper om = new ObjectMapper();
        Map<String, Map<String,String>> map2=null;
        Map<String, String> map3=null;
        String name = null;
        try {
            map2 = om.readValue(response.getBody(), Map.class);
            name = map2.get("kakao_account").get("email");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return name;
    }






}