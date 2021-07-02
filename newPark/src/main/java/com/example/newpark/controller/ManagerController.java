package com.example.newpark.controller;

import com.example.newpark.domain.Manager;
import com.example.newpark.domain.Member;
import com.example.newpark.domain.Park;
import com.example.newpark.domain.PaymentLogs;
import com.example.newpark.service.ManagerService;
import com.example.newpark.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final MemberService memberService;




    @GetMapping("/managerMain")
    public String main(HttpServletResponse response) throws Exception{

        return "manager/main";
    }



    @GetMapping("managerLogin")
    public String login(){
        return "manager/managerLogin";
    }

    @PostMapping("login")
    @ResponseBody
    public int managerLogin(@RequestBody Map<String,String> manager, HttpServletResponse response,HttpServletRequest request)  {
        try {
            String token = managerService.login(manager);
            String reToken = managerService.reToken(manager);
            Cookie cookie= new Cookie("token",token);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("token",token);
            return 1;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @GetMapping("/managerJoin")
    public String join(){
        return "manager/join";
    }

    @PostMapping("/manager/join")
    @ResponseBody
    public int managerJoin(@RequestBody Manager manager){

        try{
            return managerService.join(manager);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @GetMapping("/managerDelete")
    public String delete(){
        return "manager/delete";
    }

    @PostMapping("/manager/delete")
    @ResponseBody
    public int managerDelete(@RequestBody Manager manager){

        try{
            return managerService.delete(manager);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @PostMapping("manager/memberUpdate")
    @ResponseBody
    public int update(){
        try{
            int min = LocalDateTime.now().getMinute();
            if(min == 0) return memberService.clock();
            if(min == 15) return memberService.clock();
            if(min == 30) return memberService.clock();
            if(min == 45) return memberService.clock();
            return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -2;
        }
    }

    @GetMapping("manager/clock")
    public String clock(){
        return "manager/clock";
    }




    @PostMapping("manager/managerList")
    @ResponseBody
    public List<Manager> managersList(HttpServletResponse response, HttpServletRequest request){
        List<Manager> list = null;
        try{
            HttpSession session = request.getSession();
            String mid = (String) session.getAttribute("id");
            System.out.println(mid);
            list = managerService.findAll();


        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @PostMapping("manager/paymentList")
    @ResponseBody
    public List<PaymentLogs> pyList(HttpServletResponse response, HttpServletRequest request){
        List<PaymentLogs> list = null;
        try{
            HttpSession session = request.getSession();
            String mid = (String) session.getAttribute("id");
            System.out.println(mid);
            list = managerService.findPayAll();


        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @PostMapping("manager/memberList")
    @ResponseBody
    public List<Member> mList(HttpServletRequest request){
        List<Member> list = null;
        try{
            list = managerService.findMemAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }



    @PostMapping("/out")
    @ResponseBody
    public int logout(HttpServletResponse response, HttpServletRequest request,@CookieValue(value="id", required=false) Cookie cookie){
        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("asd");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);


        return 1;
    }
//
//    @PostMapping("out")
//    @ResponseBody
//    public void out(){
//        oauthService.out();
//    }




}
