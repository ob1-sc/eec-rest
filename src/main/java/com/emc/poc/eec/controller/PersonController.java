package com.emc.poc.eec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @RequestMapping(value="/person", method=RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }
}
