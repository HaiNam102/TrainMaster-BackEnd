package com.example.springmvc.rest.UIController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(Model model){
        return "login";
    }

    @GetMapping("/error403")
    public String error403(Model model){return "error";}
}
