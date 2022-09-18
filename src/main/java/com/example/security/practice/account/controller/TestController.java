package com.example.security.practice.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/hello")
    public String test() { return "hello"; }

}
