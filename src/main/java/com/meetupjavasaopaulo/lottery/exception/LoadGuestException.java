package com.meetupjavasaopaulo.lottery.exception;

import java.io.IOException;

public class LoadGuestException extends RuntimeException {
    public LoadGuestException(String s, Throwable t) {
        super(s, t);
    }
}
