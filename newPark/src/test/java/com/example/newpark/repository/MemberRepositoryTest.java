package com.example.newpark.repository;

import com.example.newpark.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void create(){
        Member member = new Member();
        member.setCarNumber("123");
        member.setMemberGrade("normal");
        Member insertMember = memberRepository.save(member);

    }

}