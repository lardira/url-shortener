package com.github.lardira.url_shortener.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("baseEncoder")
public class Base62Encoder implements BaseEncoder {
    private static final String symbols = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String encode(int number) {
        int base = 62;
        StringBuilder outputBuilder = new StringBuilder(1);

        do {
            outputBuilder.insert(0, symbols.charAt(number % base));
            number /= base;
        } while (number > 0);
        return outputBuilder.toString();
    }
}
