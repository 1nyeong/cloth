package com.study.clothclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/")
    public String mainpage(){
        return "page/main";
    }

    @GetMapping("/Q&A")
    public String qna(){
        return "page/qna";
    }

    @GetMapping("/notice")
    public String notice(){
        return "page/notice";
    }

    @GetMapping("/productinfo")
    public String productinfo(){
        return "page/productinfo";
    }
}
