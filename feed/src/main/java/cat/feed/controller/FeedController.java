package cat.feed.controller;

import cat.feed.entity.Feed;
import cat.feed.jwt.JwtTokenProvider;
import cat.feed.service.FeedService;
import cat.feed.service.UserService;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @GetMapping("favicon.ico") @ResponseBody public void returnNoFavicon() { }


    @GetMapping("/feed")
    public String feed(Model model, @CookieValue(value="token", required=false) Cookie cookie){
        List<Feed> list = new ArrayList<>();
        list = feedService.AllFeed();
        model.addAttribute(list);
        System.out.println(list);
        try{
            String token = cookie.getValue();
            String user = jwtTokenProvider.getUserPk(token);
            String nickName = userService.nickName(user);
                return "/feed/feed";
            }catch (Exception e){
            model.addAttribute("user","게스트");
            return "/feed/feed";
        }
    }

    @GetMapping("/feed/new")
    public String newFeed(@CookieValue(value="token", required=false) Cookie cookies){
        try {
            String id = cookies.getValue();
            return "/feed/newFeed";
        }catch (Exception e){
            return "login";
        }
    }

    @GetMapping("/feed/{title}")
    public String feedDetail(@CookieValue(value="token", required=false) Cookie cookies,
                             @PathVariable(name = "title") String title,Model model){
        try {
            String id = cookies.getValue();
            Feed feed = new Feed();
            feed = feedService.feedDetail(title,feed);
            model.addAttribute(feed);
            String token = cookies.getValue();
            String user = jwtTokenProvider.getUserPk(token);
            String nickName = userService.nickName(user);
            model.addAttribute("user",nickName);
            model.addAttribute("id",userService.id(user));
            System.out.println(feed);
            model.addAttribute("title",title);
            return "/feed/feedDetail";
        }catch (Exception e){
            return "login";
        }
    }

//    @PostMapping("/feed/{title}/view")
//    public Feed view(@CookieValue(value="token", required=false) Cookie cookies,
//                           @PathVariable(name = "title") String title, Feed list){
//        try{
//            System.out.println(title);
//            list = feedService.feedDetail(title,list);
//            System.out.println(list);
//            return list;
//        }catch (Exception e){
//            return list;
//        }
//
//    }


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

    @PostMapping("/allFeed")
    @ResponseBody
    public List<Feed> allFeed(){
        List<Feed> list = new ArrayList<>();
        list = feedService.AllFeed();
        return list;
    }






}
