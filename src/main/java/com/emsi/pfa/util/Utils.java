package com.emsi.pfa.util;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    private final static Random RANDOM=new SecureRandom();
    private final static String ALPHANUM="01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz";
    public static String genereteRandomString(int length)
    {
        StringBuilder returnValue=new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHANUM.charAt(RANDOM.nextInt(ALPHANUM.length())));
        }
        return new String(returnValue);
    }
    public static Map<String,String> getCurrentUserEmail()
    {
        Map<String,String> headers=new HashMap<String,String>(); headers.put("email","ayyoub@gmail.com");
        return headers;
    }

}
