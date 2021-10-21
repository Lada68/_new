package com.amr.project.webapp.controller;

import com.amr.project.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @GetMapping("/signup")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new UserDto());//new UserDto()
        modelAndView.setViewName("/signup");
        return modelAndView;
    }

}
