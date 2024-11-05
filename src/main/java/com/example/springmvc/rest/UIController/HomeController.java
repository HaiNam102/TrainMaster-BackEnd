package com.example.springmvc.rest.UIController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {
    @GetMapping("/index")
    public String showHomePage(Model model){
        return "index";
    }
}
