package com.rio.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @GetMapping("/ping-admin")
  public ResponseDto pingAdmin() {
    return new ResponseDto("Hello Admin!");
  }

  @GetMapping("/ping-employee")
  public ResponseDto pingEmployee() {
    return new ResponseDto("Hello Employee!");
  }

  @GetMapping("/ping-guest")
  public ResponseDto pingGuest() {
    return new ResponseDto("Hello Guest!");
  }

  @Data
  private static class ResponseDto {
    private final String message;
  }
}