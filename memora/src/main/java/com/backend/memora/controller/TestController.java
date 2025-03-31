package com.backend.memora.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/protected")
    @PreAuthorize("hasAuthority('USER')") // Ensures only authenticated users can access
    public String protectedEndpoint() {
        return "You have accessed a protected resource!";
    }
}
