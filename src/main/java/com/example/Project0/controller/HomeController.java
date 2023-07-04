package com.example.Project0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    //페이지의 홈을 담당하는 콘트롤러
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String home() {
        return "index.html";
    }
}
