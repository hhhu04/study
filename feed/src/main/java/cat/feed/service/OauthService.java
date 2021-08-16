package cat.feed.service;

import cat.feed.oauth.KakaoOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final HttpServletResponse response;
    private final KakaoOauth kakaoOauth;
    private final KakaoOauth googleOauth;

    public void request(String socialLoginType) {
        String redirectURL;
        if (socialLoginType.equals("google")) redirectURL = googleOauth.getOauthRedirectURL();
        else redirectURL = kakaoOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(String socialLoginType, String code) {

        if (socialLoginType.equals("google")) return googleOauth.requestAccessToken(code);
        else {
            return kakaoOauth.requestAccessToken(code);
        }
    }


    public void kakaoLogout() {

        try {
            response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=b4dad0c3fd74414e64580f182c1e5df9&logout_redirect_uri=http://localhost:8080/logout/kakao");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        kakaoOauth.logout();
    }
}