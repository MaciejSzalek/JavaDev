package com.javadev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalController {


    @RequestMapping("/login/result")
    public ModelAndView loginPage(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        if(request.isUserInRole("ADMIN")){
            mav.setViewName("redirect:/admin/home");
        }else if (request.isUserInRole("STUDENT")){
            mav.setViewName("redirect:/student/home");
        }
        return mav;
    }
}