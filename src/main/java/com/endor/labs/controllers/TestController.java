package com.endor.labs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestController {

    /**
     * This method is only for testing whether our application is running or not.
     * @return
     */
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "PONG";
    }
}
