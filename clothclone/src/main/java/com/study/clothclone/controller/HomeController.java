package com.study.clothclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
    }
    @GetMapping("/join")
    public String join(){
        return "join/join";
    }

    @GetMapping("/")
    public String mainpage(){
        return "page/main";
    }
    @GetMapping("/rproduct")
    public String rproduct(){
        return "productpage/rproduct";
    }


    @GetMapping("/Q&A")
    public String qna(){
        return "page/qna";
    }

    @GetMapping("/notice")
    public String notice(){
        return "page/notice";
    }

    @GetMapping("/cart")
    public String cart(){
        return "cart/cart";
    }

}
