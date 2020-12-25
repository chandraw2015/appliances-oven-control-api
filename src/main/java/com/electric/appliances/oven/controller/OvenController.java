package com.electric.appliances.oven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OvenController {

    @GetMapping("/get/message")
    public String getMessage(){

        return "Hello My World";
    }
}
