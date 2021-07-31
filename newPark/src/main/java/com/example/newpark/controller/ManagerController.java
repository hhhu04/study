package com.example.newpark.controller;

import com.example.newpark.entity.Manager;
import com.example.newpark.entity.Member;
import com.example.newpark.entity.PaymentLogs;
import com.example.newpark.dto.Master;
import com.example.newpark.service.ManagerService;
import com.example.newpark.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final MemberService memberService;

    @GetMapping("/manager")
    public String main(HttpServletResponse response, Model model, Principal principal) throws Exception{
        if(principal.getName().equals("master")){
            model.addAttribute("master","master");
        }
        return "manager/main";
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

    @GetMapping("masterCheck")
    public String masterCheck(){

        return "manager/masterLogin";
    }

    @PostMapping("manager/check")
    @ResponseBody
    public int check(@RequestBody Master master, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            int num = managerService.master(master);
            if(num == 1){
                session.setAttribute("id",master.getId());
            }
            return num;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @GetMapping("clock")
    public String clock(HttpServletRequest request, Principal principal){
        HttpSession session = request.getSession();
        if(principal.getName().equals("master")) session.setAttribute("id","master");
        try {
            memberService.clock();
            session.setMaxInactiveInterval(60*60);
            if(session.getAttribute("id").equals("master")) new Exception();
            return "manager/clock";
        }catch (Exception e){
            e.printStackTrace();
            return "manager/clockError";
        }
    }


    @PostMapping("/mail")
    @ResponseBody
    public String sendMail(){
        try {
            managerService.mailSend();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "redirect:http://localhost:8080/";
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


}
