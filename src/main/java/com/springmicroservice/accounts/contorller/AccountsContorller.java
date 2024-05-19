package com.springmicroservice.accounts.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsContorller {


    @GetMapping("/sayhello")
    public String helloWorld(){
        return "Hello world";
    }
}
