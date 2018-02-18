package com.meetupjavasaopaulo.lottery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Value("classpath:meetup.xls")
    private Resource res;

    @GetMapping("/")
    public ModelAndView home() {

        List<String> guests = new ArrayList<>();
        String winner = "";
        int lottery = 0;

        System.out.println("All files:");
        try (Stream<String> stream = Files.lines(Paths.get(res.getURI()))) {

            guests = stream.collect(Collectors.toList());

            // guests.forEach(System.out::println);

            System.out.println("Stream size: " + guests.size());

            lottery = RandomUtil.getRandomNumberInRange(0, guests.size());
            winner = guests.get(lottery);

            System.out.println("Winner: " + winner);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("home");
        mv.addObject("guests", guests);
        mv.addObject("size", guests.size());
        mv.addObject("winner", winner);
        mv.addObject("lottery", lottery);

        return mv;

    }

}
