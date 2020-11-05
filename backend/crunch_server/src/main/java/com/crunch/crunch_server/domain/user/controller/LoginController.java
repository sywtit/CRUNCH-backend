package com.crunch.crunch_server.domain.user.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    public String login(@AuthenticationPrincipal User user){
        //make guest login
        // if(user != null) {
        //     return "redirect:/";
        //   }
        //return "login/api/";

        return "redirect:/";
      }

}
