package com.example.newpark.service;

import com.example.newpark.domain.Manager;
import com.example.newpark.domain.Member;
import com.example.newpark.domain.PaymentLogs;
import com.example.newpark.repository.ManagerRepositor;
import com.example.newpark.repository.MemberRepository;
import com.example.newpark.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepositor managerRepositor;
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;


    public int login(Manager manager) throws Exception{
        Long id = managerRepositor.findManagerByManagerIdAndPassword(manager.getManagerId(), manager.getPassword()).getId();
        System.out.println(manager);
        if(id == null) return 0;
        return 1;
    }


    public List<Manager> findAll() throws Exception{
        List<Manager> list;
        list = managerRepositor.findAll();
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
}
