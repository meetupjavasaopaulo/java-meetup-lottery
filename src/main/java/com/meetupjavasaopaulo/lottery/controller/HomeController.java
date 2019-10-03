package com.meetupjavasaopaulo.lottery.controller;

import com.meetupjavasaopaulo.lottery.util.LoadGuestList;
import com.meetupjavasaopaulo.lottery.util.RandomUtil;
import com.meetupjavasaopaulo.lottery.util.StoreWinners;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private static List<String> guests = LoadGuestList.loadGuest();

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);
    private boolean random = false;

    @GetMapping("/")
    public ModelAndView home() {
        List<String> baseGuests = LoadGuestList.loadGuest();
        String winner = "";
        int lottery = 0;

        logger.info("Randomizing a winner...");
        while (randomAgain(winner)){
            lottery = RandomUtil.getRandomNumberInRange(0, guests.size() - 1);
            winner = guests.get(lottery);
            lottery = baseGuests.indexOf(winner) + 1;
            guests.remove(winner);
        }

        if (!StringUtils.isBlank(winner)){
            StoreWinners.setWinner(winner);
        }

        ModelAndView mv = new ModelAndView("home");
        mv.addObject("guests", baseGuests);
        mv.addObject("remaining", guests.size());
        mv.addObject("size", baseGuests.size());
        mv.addObject("lottery", lottery);
        mv.addObject("winner", winner);
        mv.addObject("winners", StoreWinners.getWinners());


        random = false;
        return mv;
    }
    private boolean randomAgain(String winner){
        return  StringUtils.isBlank(winner) && !guests.isEmpty() && random;
    }

    @GetMapping("/about")
    public String sobre() {
        return "about";
    }

    @PostMapping("/reset")
    public String reset(){
        guests = LoadGuestList.loadGuest();
        StoreWinners.reset();
        return "redirect:/";
    }

    @PostMapping("/random")
    public String random(){
        random = true;
        return "redirect:/";
    }

}
