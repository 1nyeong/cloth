package com.study.clothclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login/login";
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

    @GetMapping("/cart")
    public String cart(){
        return "cart/cart";
    }

    @GetMapping("/productinfo1")
    public String productinfo1(){
        return "page/productinfo1";
    }
    @GetMapping("/productinfo2")
    public String productinfo2(){
        return "page/productinfo2";
    }
    @GetMapping("/productinfo3")
    public String productinfo3(){
        return "page/productinfo3";
    }
    @GetMapping("/productinfo4")
    public String productinfo4(){
        return "page/productinfo4";
    }
    @GetMapping("/productinfo5")
    public String productinfo5(){
        return "page/productinfo5";
    }
    @GetMapping("/productinfo6")
    public String productinfo6(){
        return "page/productinfo6";
    }
}
