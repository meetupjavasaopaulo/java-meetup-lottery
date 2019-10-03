package com.meetupjavasaopaulo.lottery.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreWinners {

    private static List<String> winners = new ArrayList<>();

    public static void setWinner(String winner) {
        winners.add(winner);
    }

    public static List<String> getWinners(){
        return winners;
    }

    public static void reset(){
        winners = new ArrayList<>();
    }

}
