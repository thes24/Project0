package com.example.Project0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    //페이지의 홈을 담당하는 콘트롤러
    @RequestMapping("/")
    public String home() {
        return "index.html";
    }
}
