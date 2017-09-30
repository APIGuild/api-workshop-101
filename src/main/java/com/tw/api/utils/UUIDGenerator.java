package com.tw.api.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UUIDGenerator {

    private int idLength;

    @Autowired
    public UUIDGenerator(@Value("${id.length:8}") int idLength) {
        this.idLength = idLength;
    }

    public String generate() {
        return RandomStringUtils.randomAlphanumeric(idLength);
    }
}
