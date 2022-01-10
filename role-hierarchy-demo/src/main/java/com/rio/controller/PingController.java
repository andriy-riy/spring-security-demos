package com.rio.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'GUEST')")
    @GetMapping("/ping-guest")
    public ResponseDto pingGuest() {
        return new ResponseDto("Hello Guest!");
    }

    private record ResponseDto(String message) {
    }
}