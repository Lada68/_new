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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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

    @PostMapping("/user/newShop")
    public String createNewShop(Principal principal,
                                @ModelAttribute("country") String location,
                                @ModelAttribute("cityLocation") String cityLocation,
                                @ModelAttribute("name") String name,
                                @ModelAttribute("phone") String phone,
                                @ModelAttribute("email") String email,
                                @ModelAttribute("description") String description) {
        Shop shop = new Shop();
        shop.setEmail(email);
        shop.setDescription(description);
        shop.setPhone(phone);
        shop.setName(name);
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
    @GetMapping("/user/market/{id}")
    public String marketHome(Model model, @PathVariable(value = "id", required = true) Long id) {
        if (shopService.existsById(Shop.class, id)) {
            return "market";
        }
        return "404";
    }
//    @GetMapping("/updateShop/{id}")
//    public ModelAndView getUpdateShop(@ModelAttribute Shop shop, @PathVariable("id") Long id){
//        System.out.println(shop);
//       System.out.println(id);
//        shop = shopService.findById(id);
//        System.out.println(shop);
//     //   shopDto = shopMapper.shopToDto(shop);
//        ModelAndView modelAndView = new ModelAndView();
//       modelAndView.addObject("shop", shop);
//        modelAndView.setViewName("updateShop");
//        return modelAndView;
//    }

    @RequestMapping(value = "/updateShop", method = {RequestMethod.PUT, RequestMethod.GET})
    public ModelAndView updateShop(Principal principal, @ModelAttribute Shop shop,
                             @ModelAttribute("country") String location,
                             @ModelAttribute("cityLocation") String cityLocation){
        System.out.println(location);
        System.out.println(cityLocation);
        System.out.println(principal);
        Country country = countryService.findByName(location);
        if(country == null){
        countryService.addNewCountry(new Country(location));}
        shop.setLocation(country);
        City city = cityService.findByName(cityLocation);
        if(city == null){
            cityService.addNewCity(new City(cityLocation, country));
        }
        shop.setCity(city);
       //Shop shop = shopMapper.dtoToModel(shopDto);
//        User user = userService.findUserByUsername(principal.getName());
//        user.addShop(shop);
        shopService.update(shop);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("shop", shop);
        modelAndView.setViewName("user");
//        modelAndView.addObject("user", );
        return modelAndView;
    }


}
