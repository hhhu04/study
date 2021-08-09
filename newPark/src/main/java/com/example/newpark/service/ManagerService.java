package com.example.newpark.service;

import com.example.newpark.entity.Manager;
import com.example.newpark.entity.Member;
import com.example.newpark.entity.PaymentLogs;
import com.example.newpark.dto.Master;
import com.example.newpark.repository.ManagerRepositoy;
import com.example.newpark.repository.MemberRepository;
import com.example.newpark.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepositoy managerRepositoy;
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public int master(Master master) throws Exception{
        System.out.println(master);
        String grade = managerRepositoy.findByManagerId(master.getId()).getGrade();
        if(grade.equals("master")) return 1;
        else return 0;
    }

    public int join(Manager manager) throws Exception{
         manager = manager.join(manager,passwordEncoder);
         if(!managerRepositoy.existsManagerByManagerId(manager.getManagerId())) {
            managerRepositoy.save(manager);
            return  1;
        }
         return -2;
    }

    public int delete(Manager manager)throws Exception{
        if(managerRepositoy.existsManagerByManagerId(manager.getManagerId())){
            if(manager.getManagerId().equals("123")) return -2;
            manager = managerRepositoy.findByManagerId(manager.getManagerId());
            managerRepositoy.delete(manager);
            return 1;
        }
        return -1;
    }



    public List<Manager> findAll() throws Exception{
        List<Manager> list;
        list = managerRepositoy.findAll();
        return list;
    }

    public List<PaymentLogs> findPayAll() throws Exception{
        List<PaymentLogs> list;
        list = paymentRepository.findAll();
        return list;
    }

    public List<Member> findMemAll() throws Exception{
        List<Member> list = null;
        list = memberRepository.findMemberByMemberGrade("member");
        return list;
    }


    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ShinTest94@gmail.com";
    private static final String SET_ADDRESS = "hhhu04@gmail.com";

    public void mailSend() throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(SET_ADDRESS);
        message.setFrom(FROM_ADDRESS);
        message.setSubject("요금 갱신 오류");
        message.setText("error");
        mailSender.send(message);

    }


}
