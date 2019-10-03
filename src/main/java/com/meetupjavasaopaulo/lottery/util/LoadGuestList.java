package com.meetupjavasaopaulo.lottery.util;

import com.meetupjavasaopaulo.lottery.exception.LoadGuestException;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LoadGuestList {

    private final static String resourceUrl = "classpath:meetup.xls";
    private final static String emptyString = "";

     public static List<String> loadGuest(){
        try {
            Stream<String> stream = Files.lines(Paths.get(loadResource().getPath()));
            List<String> guestList = stream.collect(Collectors.toList());
            guestList.removeAll(Collections.singleton(emptyString));
            return  guestList;
        } catch (IOException ioe) {
            throw new LoadGuestException("Failure loading guest list" + ioe.getMessage(), ioe);
        }
    }

    private static File loadResource() throws FileNotFoundException {
        return ResourceUtils.getFile(resourceUrl);
    }

}
