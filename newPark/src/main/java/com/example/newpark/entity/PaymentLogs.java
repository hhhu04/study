package com.example.newpark.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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

    private String paymentNumber;


    public PaymentLogs insetLog(PaymentLogs paymentLogs,Member member) throws Exception{
        paymentLogs.setMemberId(member.getId());
        paymentLogs.setPrice(member.getExpectedPayment());
        paymentLogs.setPaymentStatus("success");
        paymentLogs.setPaymentDate(LocalDateTime.now());

        Date dt = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyMMddHHmmss");
        String number = date.format(dt);
        paymentLogs.setPaymentNumber(number);

        return paymentLogs;
    }






}
