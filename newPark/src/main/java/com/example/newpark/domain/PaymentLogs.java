package com.example.newpark.domain;

import com.example.newpark.config.RemainPark;
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
public class PaymentLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private int price;

    private String paymentStatus;

    private String statusComment;

    private LocalDateTime paymentDate;


    public PaymentLogs insetLog(PaymentLogs paymentLogs,Member member) throws Exception{
        paymentLogs.setMemberId(member.getId());
        paymentLogs.setPrice(member.getExpectedPayment());
        paymentLogs.setPaymentStatus("success");
        paymentLogs.setPaymentDate(LocalDateTime.now());
        return paymentLogs;
    }






}
