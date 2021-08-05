package com.example.newpark.entity;


import com.example.newpark.dto.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String carNumber;

    private String memberGrade;

    private LocalDateTime joinAt;

    private LocalDateTime expireAt;

    private int expectedPayment;

    private LocalDateTime inDate;

    private LocalDateTime outDate;

    private String paymentStatus;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private List<ParkLogs> park = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private List<PaymentLogs> payment = new ArrayList<>();


    public Member createInput(Member member) throws Exception{
        member.setMemberGrade("normal");
        member.setExpectedPayment(0);
        member.setInDate(LocalDateTime.now());
        member.setPaymentStatus("not");
        return member;
    }

    public Member updateInput(Member member) throws Exception{
        member.setInDate(LocalDateTime.now());
        if(member.getMemberGrade().equals("normal")) {
            member.setPaymentStatus("not");
            member.setExpectedPayment(0);
        }
        return member;
    }

    public Member updateGrade(Member member, Card card) throws Exception{
        member.setMemberGrade("member");
        member.setExpectedPayment(0);
        member.setPaymentStatus("okay");
        member.setJoinAt(LocalDateTime.now());
        LocalDateTime time = member.getJoinAt();
        if(card.getCheck().equals("1")) member.setExpireAt(time.plusDays(7));
        if(card.getCheck().equals("2")) member.setExpireAt(time.plusMonths(1));
        if(card.getCheck().equals("3")) member.setExpireAt(time.plusMonths(3));
        return member;
    }

    public Member updatePayment(Member member) throws Exception{
        member.setPaymentStatus("okay");
        member.setExpectedPayment(0);
        return member;
    }

    public Member out(Member member) throws Exception{
        member.setOutDate(LocalDateTime.now());
        member.setPaymentStatus("null");
        return member;
    }

    public boolean number(Member member) throws Exception{
        String pattern = "\\d{2}+[가-힣]+\\d{4}";
        String pattern2 = "\\d{3}+[가-힣]+\\d{4}";
        boolean result = Pattern.matches(pattern,member.carNumber);
        if(result)return true;
        result = Pattern.matches(pattern2,member.carNumber);
        return result;
    }


}
