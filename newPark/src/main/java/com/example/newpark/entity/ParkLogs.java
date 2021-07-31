package com.example.newpark.entity;

import com.example.newpark.config.RemainPark;
import com.example.newpark.dto.Park;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private LocalDateTime date;

    private String paymentStatus;

    private String logStatus;


    public ParkLogs parkLog(ParkLogs parkLogs,Member member) throws Exception {
        parkLogs.setMemberId(member.getId());

        parkLogs.setDate(LocalDateTime.now());
        Park park = new Park();
        RemainPark remainPark = new RemainPark();
        park = remainPark.readSetting();
        if(parkLogs.getLogStatus().equals("in")) park.setRemain(park.getRemain()-1);
        else if(parkLogs.getLogStatus().equals("out")) park.setRemain(park.getRemain()+1);
        remainPark.writeSetting(park);
        return parkLogs;
    }




}
