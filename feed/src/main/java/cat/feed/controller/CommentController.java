package cat.feed.controller;

import cat.feed.entity.Comment;
import cat.feed.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/new")
    @ResponseBody
    public int newComment(@RequestBody Comment comment){
        try{
            System.out.println(comment);
            comment=comment.newComment(comment);
            commentService.newComment(comment);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @PostMapping("/all/comment")
    @ResponseBody
    public List<Comment> allComment(){
        List<Comment> list = new ArrayList<>();
        try{
            list = commentService.allComment(list);
            return list;
        }catch (Exception e) {
            return list;
        }
    }


}
