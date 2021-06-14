package com.example.newpark.service;

import com.example.newpark.auth.SocialLoginType;
import com.example.newpark.auth.SocialOauth;
import com.example.newpark.domain.Manager;
import com.example.newpark.repository.ManagerRepositor;
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
    private final ManagerRepositor managerRepositor;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Manager requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);

        Manager manager = new Manager();

        String name = socialOauth.requestAccessToken(code);
        System.out.println(managerRepositor.existsManagerByName(name));
        if(managerRepositor.existsManagerByName(name)) {
            manager = managerRepositor.findManagerByName(name);
            return manager;
        }
        else return null;


    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
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
