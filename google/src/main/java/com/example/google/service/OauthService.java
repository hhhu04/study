package com.example.google.service;

import com.example.google.domain.GoogleOauth;
import com.example.google.domain.KakaoOauth;
import com.example.google.domain.SocialLoginType;
import com.example.google.domain.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;
    private final KakaoOauth kakaoOauth;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);

        return socialOauth.requestAccessToken(code);
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

    public void kakaoLogout(){

        try {
            response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=b4dad0c3fd74414e64580f182c1e5df9&logout_redirect_uri=http://localhost:8080/auth/logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        kakaoOauth.logout();
    }



//    private final GoogleOauth googleOauth;
//    private final KakaoOauth kakaoOauth;
//    private final HttpServletResponse response;
//
//    public void request(SocialLoginType socialLoginType) {
//        String redirectURL;
//        switch (socialLoginType) {
//            case GOOGLE: {
//                redirectURL = googleOauth.getOauthRedirectURL();
//            } break;
//            case KAKAO: {
//                redirectURL = kakaoOauth.getOauthRedirectURL();
//            } break;
//            default: {
//                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
//            }
//        }
//        try {
//            response.sendRedirect(redirectURL);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
//        switch (socialLoginType) {
//            case GOOGLE: {
//                return googleOauth.requestAccessToken(code);
//            }
//            case KAKAO: {
//                return kakaoOauth.requestAccessToken(code);
//            }
//            default: {
//                throw new IllegalArgumentException("알 수 없는 소셜 로그인 형식입니다.");
//            }
//        }
//    }

}
