package com.example.newpark.service;

import com.example.newpark.config.RemainPark;
import com.example.newpark.dto.Card;
import com.example.newpark.entity.Member;
import com.example.newpark.entity.ParkLogs;
import com.example.newpark.repository.MemberRepository;
import com.example.newpark.repository.ParkLogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ParkLogsRepository parkLogsRepository;
    private final RemainPark remainPark;


    public Member findCar(Member member) throws Exception{
        member = memberRepository.findMemberByCarNumber(member.getCarNumber());
        return member;
    }

    public Long findNumber(String carNumber) throws Exception{
        return memberRepository.findMemberByCarNumber(carNumber).getId();
    }

    public int insert(Member member) throws Exception{
        if(memberRepository.findMemberByCarNumber(member.getCarNumber()) == null){
            member = member.createInput(member);
        }
        else {
            member = memberRepository.findMemberByCarNumber(member.getCarNumber());
            ParkLogs parkLogs = new ParkLogs();
            parkLogs = parkLogsRepository.findByMemberIdOrderByIdDesc(member.getId()).get(0);

            if(parkLogs.getLogStatus().equals("out")) member = member.updateInput(member);
            else if(parkLogs.getLogStatus().equals("in")) return -2;
        }
        memberRepository.save(member);
        ParkLogs parkLogs = new ParkLogs();
        parkLogs.setLogStatus("in");
        parkLogs = parkLogs.parkLog(parkLogs,member);
        System.out.println(parkLogs);
        parkLogsRepository.save(parkLogs);
        return 1;
    }



    public int update(Member member) throws Exception{
        member = member.updatePayment(member);
        memberRepository.save(member);
        return 1;
    }


    public int out(Member member) throws Exception{
        ParkLogs parkLogs = new ParkLogs();
        parkLogs = parkLogsRepository.findByMemberIdOrderByIdDesc(member.getId()).get(0);
        if(parkLogs.getLogStatus().equals("out")) return -2;

        parkLogs.setLogStatus("out");
        parkLogs.setPaymentStatus(member.getPaymentStatus());
        parkLogs = parkLogs.parkLog(parkLogs,member);
        parkLogsRepository.save(parkLogs);
        member = member.out(member);
        memberRepository.save(member);
        return 1;
    }


    public int memberJoin(Card card,Member member) throws Exception{
        int num = card.check(card);
        if(card.getCarNumber().equals("")) num = -3;
        member = memberRepository.findMemberByCarNumber(card.getCarNumber());
        member = member.updateGrade(member,card);
        memberRepository.save(member);
        return num;
    }


    public int clock() throws Exception {
        List<Member> list= new ArrayList<>();
        list = memberRepository.findMemberByMemberGrade("normal");

        for (int i=0;i<list.size();i++){
            int money = list.get(i).getExpectedPayment();
            LocalDateTime time = list.get(i).getInDate();
            if(time.getDayOfMonth() == LocalDateTime.now().getDayOfMonth() ) {
                if(money < 20000) money+=1500;
            }
            else {
                if(LocalDateTime.now().getHour() == 00 && LocalDateTime.now().getMinute() == 00) {
                    money += 20000;
                }
            }
            list.get(i).setExpectedPayment(money);
        }
        memberRepository.saveAll(list);
        return 1;
    }







}
