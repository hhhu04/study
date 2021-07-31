package com.example.newpark.controller;

import com.example.newpark.dto.Card;
import com.example.newpark.entity.Member;
import com.example.newpark.entity.PaymentLogs;
import com.example.newpark.service.MemberService;
import com.example.newpark.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces="application/json; charset=utf-8")
public class PaymentController {

    private final MemberService memberService;
    private final PaymentService paymentService;

    @GetMapping("checkCar")
    public String checkCar(){
        return "payment/checkCar";
    }

    @PostMapping("checkCar/check")
    @ResponseBody
    public int checkCar(@RequestBody Member member, HttpServletRequest request, HttpServletResponse response){
        try {
            if(!member.number(member)) return 0;
            member = memberService.findCar(member);
            Cookie cookie = new Cookie("carNumber",member.getCarNumber());
            System.out.println(cookie.getValue());
            response.addCookie(cookie);

            HttpSession session = request.getSession();

            session.setAttribute("carNumber",member.getCarNumber());

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    @GetMapping("payment")
    public String payment(Model model,@CookieValue(value="carNumber", required=false) Cookie cookie){
        try {
            Member member = new Member();
            member.setCarNumber(cookie.getValue());
            member = memberService.findCar(member);
            model.addAttribute("member", member);
            return "payment/payment";
        }catch (NullPointerException e) {
            e.printStackTrace();
            return "redirect:checkCar";

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("payment/payment")
    @ResponseBody
    public int paymentStart(@RequestBody Card card, @CookieValue(value="carNumber", required=false) Cookie cookie,PaymentLogs paymentLogs,Member member,
                                HttpServletRequest request,HttpServletResponse response){
        try {
            member.setCarNumber(cookie.getValue());
            member = memberService.findCar(member);
            int num = paymentService.createPaymentLog(member, paymentLogs,card);
            memberService.update(member);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            HttpSession session = request.getSession();
            session.invalidate();

            return num;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


    }












}
