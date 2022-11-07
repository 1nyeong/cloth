package com.study.clothclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/all")
    public String page(){
        return "page/collections_scroll";
    }

    @GetMapping("/notice")
    public String cart(){
        return "page/notice";
    }




}
