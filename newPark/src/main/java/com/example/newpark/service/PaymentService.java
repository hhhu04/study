package com.example.newpark.service;

import com.example.newpark.domain.Card;
import com.example.newpark.domain.Member;
import com.example.newpark.domain.PaymentLogs;
import com.example.newpark.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public int createPaymentLog(Member member, PaymentLogs paymentLogs,Card card) throws Exception{
        int check = card.check(card);
        if(check == 1){
            paymentLogs = paymentLogs.insetLog(paymentLogs,member);
            paymentRepository.save(paymentLogs);
            return 1;
        }
        if(check == -2) return -2;
        return -1;
    }


}
