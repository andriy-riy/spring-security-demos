package com.rio.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @GetMapping("/ping")
  public PingDto ping() {
    return new PingDto("Bingo!");
  }

  @Data
  private static class PingDto {
    private final String message;
  }
}