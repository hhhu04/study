package com.example.newpark.controller;

import com.example.newpark.domain.Manager;
import com.example.newpark.domain.Member;
import com.example.newpark.domain.PaymentLogs;
import com.example.newpark.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("manager")
    public String main(){
        return "manager/main";
    }

    @GetMapping("manager/managerLogin")
    public String login(){
        return "manager/managerLogin";
    }

    @PostMapping("manager/login")
    @ResponseBody
    public int managerLogin(@RequestBody Manager manager, HttpServletRequest request, HttpServletResponse response){
        try{
            int num=0;
            num = managerService.login(manager);
            if(num == 1){
                Cookie cookie = new Cookie("id",manager.getManagerId());
                System.out.println(cookie.getValue());
                response.addCookie(cookie);

                HttpSession session = request.getSession();

                session.setAttribute("id",manager.getManagerId());
            }

            return num;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

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



    @PostMapping("manager/logout")
    @ResponseBody
    public int logout(HttpServletResponse response, HttpServletRequest request,@CookieValue(value="id", required=false) Cookie cookie){
        HttpSession session = request.getSession();
        session.invalidate();

        cookie.setMaxAge(0);
        response.addCookie(cookie);


        return 1;
    }




}
