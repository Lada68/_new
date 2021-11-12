package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
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
        //user.getShops();
        return modelAndView; }

    @RequestMapping("/getOne")
    @ResponseBody
    public Shop getOne(Long id){
Shop shop = shopService.findById(id);
        return shop;
    }
}
