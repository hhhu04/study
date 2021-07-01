package com.example.newpark.service;

import com.example.newpark.domain.Manager;
import com.example.newpark.domain.Member;
import com.example.newpark.domain.PaymentLogs;
import com.example.newpark.jwt.JwtTokenProvider;
import com.example.newpark.repository.ManagerRepositoy;
import com.example.newpark.repository.MemberRepository;
import com.example.newpark.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepositoy managerRepositoy;
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public String login(Map<String,String> manager) throws Exception{
        System.out.println(manager.get("password"));
        System.out.println();
        Manager mana = managerRepositoy.findByManagerId(manager.get("managerId"));
        if (managerRepositoy.findByManagerId(manager.get("managerId")) == null) throw new IllegalArgumentException("가입되지 않은 사용자 입니다.");
        if (!manager.get("password").equals(mana.getPassword())) throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        System.out.println(mana);
        return jwtTokenProvider.createToken(mana.getManagerId(),manager,mana.getRoles());
    }


    public int join(Manager manager) throws Exception{
         manager = manager.join(manager);
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
}
