package com.rio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping("/ping")
    public PingDto ping() {
        return new PingDto("Bingo!");
    }

    private record PingDto(String message) {
    }
}