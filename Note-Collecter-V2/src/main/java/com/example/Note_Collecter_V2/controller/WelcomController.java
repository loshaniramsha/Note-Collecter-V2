package com.example.Note_Collecter_V2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/welcome")

public class WelcomController {
    @GetMapping
    public String viewWelcomScreen(Model model){
        model.addAttribute("message","welcome");
        return "welcom";
    }
}
