package com.example.google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OutController {

    @GetMapping("auth/logout")
    public String logout(HttpServletRequest request){
    HttpSession session = request.getSession();
        session.invalidate();

    return "main";
    }


    @GetMapping("main")
    public String main(){
        return "main";
    }

}
