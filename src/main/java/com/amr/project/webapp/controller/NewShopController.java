package com.amr.project.webapp.controller;

import com.amr.project.converter.ShopMapper;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.Shop;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class NewShopController {
    private final ShopService shopService;
    private final UserService userService;
    private final CountryService countryService;
    private final CityService cityService;
    private final ShopMapper shopMapper;

    public NewShopController(ShopService shopService, UserService userService, CountryService countryService, CityService cityService, ShopMapper shopMapper) {
        this.shopService = shopService;
        this.userService = userService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.shopMapper = shopMapper;
    }

    @GetMapping("/newShop")
    public ModelAndView getNewShop(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        System.out.println(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("shopDto", new ShopDto());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("newShop");
        return modelAndView;
    }

    @PostMapping("/user/newShop")
    public String createNewShop(Principal principal,
                                @ModelAttribute("country") String location,
                                @ModelAttribute("cityLocation") String cityLocation,
                                @ModelAttribute ShopDto shopDto) {
        System.out.println(location);
        System.out.println(shopDto);
        System.out.println(principal);
        Shop shop = shopMapper.dtoToModel(shopDto);
        countryService.addNewCountry(new Country(location));
        cityService.addNewCity(new City(cityLocation,
                countryService.findByName(location)));
        shop.setLocation(countryService.findByName(location));
        shop.setCity(cityService.findByName(cityLocation));
        User user = userService.findUserByUsername(principal.getName());
        shop.setUser(user);
        shopService.addNewShop(shop);
        return "redirect:/user";
    }
}
