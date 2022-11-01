package com.study.clothclone.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {

    @GetMapping("/account/login")
    public String login(Model model,
                        @RequestParam @Nullable String email,
                        @RequestParam @Nullable String error){
        model.addAttribute("email", email == null ? "" : email);
        model.addAttribute("error", error == null ? "" : error);
        return "account/login";
    }

    @GetMapping("/account/register")
    public String register(){
        return "account/register";
    }

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
