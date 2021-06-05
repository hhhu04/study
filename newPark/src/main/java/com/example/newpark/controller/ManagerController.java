package com.example.newpark.controller;

import com.example.newpark.domain.Manager;
import com.example.newpark.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public int managerLogin(@RequestBody Manager manager){
        try{
            int num=0;
            num = managerService.login(manager);

            return num;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }







}
