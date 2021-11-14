package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@RestController
public class UserPageController {

    private final UserService userService;
    private final ShopService shopService;

    public UserPageController(UserService userService, ShopService shopService) {
        this.userService = userService;
        this.shopService = shopService;
    }

    @GetMapping("/user")
    public ModelAndView userPage(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", user);
        List<Shop> shops = user.getShops();
        modelAndView.addObject("shops", shops);
        return modelAndView; }

    @RequestMapping(value = "/getOne/{id}", method = RequestMethod.GET)
    public String getOne(@PathVariable  Long id) {
        Shop shop = shopService.findById(id);
        String res = shop.toString();
        return res;
    }
}
