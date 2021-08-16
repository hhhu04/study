package cat.feed.controller;

import cat.feed.entity.Feed;
import cat.feed.jwt.JwtTokenProvider;
import cat.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/feed")
    public String feed(){
        return "feed";
    }

    @GetMapping("/feed/new")
    public String newFeed(@CookieValue(value="token", required=false) Cookie cookies){
        try {
//            String id = cookies.getValue();
            return "newFeed";
        }catch (Exception e){
            return "login";
        }
    }

    @PostMapping("/user/feed")
    @ResponseBody
    public int newFeed(@RequestBody Feed feed,@CookieValue(value="token", required=false) Cookie cookies){
        try {
            String userId = jwtTokenProvider.getUserPk(cookies.getValue());
            feedService.save(feed, userId);
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

}
