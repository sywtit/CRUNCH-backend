package com.crunch.crunch_server;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class RootController implements ErrorController 
{
    @GetMapping({"/error"})
    public String getUsers() {
        return "index";
    }


    @Override
    public String getErrorPath(){
        return "/error";
    }

   private final HttpSession httpSession;

   @GetMapping("/")
   public String index() {

       return "/index";
   }


}