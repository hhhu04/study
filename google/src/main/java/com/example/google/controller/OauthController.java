package com.example.google.controller;

import com.example.google.service.OauthService;
import com.example.google.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Slf4j
public class OauthController {
    private final OauthService oauthService;
    private final UserService userService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     */
    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") String socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }


    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "/{socialLoginType}/callback")
    public String callback(
            @PathVariable(name = "socialLoginType") String socialLoginType,
            @RequestParam(name = "code") String code, HttpServletRequest request) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        String email = oauthService.requestAccessToken(socialLoginType, code);

        if(userService.check(email)) {
            userService.login(email,request);
            return "<script>alert('로그인');  window.location = 'http://localhost:8080/main'</script>";
        }
        else {
            userService.join(email);
            return "<script>alert('가입진행'); window.location = 'http://localhost:8080/main'</script>";
        }

    }

    @GetMapping("out")
    public void out(){
        oauthService.kakaoLogout();

    }




}
