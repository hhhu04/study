package com.example.newpark.controller;

import com.example.newpark.config.RemainPark;
import com.example.newpark.dto.Card;
import com.example.newpark.entity.Member;
import com.example.newpark.entity.ParkLogs;
import com.example.newpark.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(produces = "application/json; charset=utf-8")
public class MemberController {

    private final MemberService memberService;

//  1:성공 -1:error -2:중복 -3:조건식 0:결제안됨

    @GetMapping("/")
    public String main(Model model) {
        RemainPark remainPark = new RemainPark();
        try {
            model.addAttribute("remain", remainPark.readSetting());
            return "main";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    @GetMapping("/in")
    public String inCar(Model model) {

        return "inCar";
    }

    @PostMapping("inCar")
    @ResponseBody
    public int inCar(@RequestBody Member member, ParkLogs parkLogs) {
        try {
            int num = 0;

            if(!member.number(member)) return -3;

            num = memberService.insert(member);

            return num;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return -2;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    @GetMapping("out")
    public String out() {
        return "outCar";
    }


    @PostMapping("outCar")
    @ResponseBody
    public int outCar(@RequestBody Member member){
        try{
            if(!member.number(member)) return 0;
            member = memberService.findCar(member);
            if(member.getPaymentStatus().equals("not")) return 0;
            else return memberService.out(member);

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


    }


    @GetMapping("member")
    public String member() {
        return "member/member";
    }

    @PostMapping("member/join")
    @ResponseBody
    public int join(@RequestBody Card card, Member member){
        try{
            memberService.memberJoin(card, member);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }







}
