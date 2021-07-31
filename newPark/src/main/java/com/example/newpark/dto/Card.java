package com.example.newpark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    private String carNumber;

    private String card;

    private String hal;

    private String month;

    private String number;

    private String check;


    public int check(Card card) throws Exception{
        int num = 1;

        if(card.getCard().equals("선택")) num = -3;
        if(card.getMonth().equals("")) num = -3;
        if(card.getNumber().equals("")) num = -3;

        return num;
    }


}
