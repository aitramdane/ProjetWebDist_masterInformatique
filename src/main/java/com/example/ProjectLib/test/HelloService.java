package com.example.ProjectLib.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    @RequestMapping(value="/rest/test/hello", method= RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
