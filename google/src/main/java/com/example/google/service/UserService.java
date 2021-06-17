package com.example.google.service;

import com.example.google.dto.User;
import com.example.google.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public boolean check(String email){
        return userRepository.existsUserByEmail(email);
    }

    public void join(String email){
        User user = new User();
        user.setEmail(email);
        user.setSocial("kakao");

        userRepository.save(user);

    }

    public void login(String email, HttpServletRequest request){

        HttpSession session =  request.getSession();;
        session.setAttribute("id",email);

    }



}
