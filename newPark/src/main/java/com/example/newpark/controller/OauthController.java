package com.example.newpark.controller;

import com.example.newpark.auth.SocialLoginType;
import com.example.newpark.domain.Manager;
import com.example.newpark.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "")
@Slf4j
public class OauthController {
    private final OauthService oauthService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     */
    @GetMapping(value = "/auth/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }


    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "manager/{socialLoginType}/callback")
    public String callback(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
            @RequestParam(name = "code") String code, HttpServletRequest request, HttpServletResponse response) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
         Manager manager = oauthService.requestAccessToken(socialLoginType, code);
        if(manager != null) {

            Cookie cookie = new Cookie("id",manager.getManagerId());
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("id",manager.getManagerId());
            return "<script>window.location = 'http://localhost:8080/manager'</script>";
        }
        else return "<script>window.location = 'http://localhost:8080/manager/managerLogin'</script>";
    }








}
