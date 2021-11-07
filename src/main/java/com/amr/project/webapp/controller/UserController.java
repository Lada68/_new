package com.amr.project.webapp.controller;

import com.amr.project.converter.UserMapper;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.City;
import com.amr.project.model.entity.Country;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.AddressService;
import com.amr.project.service.abstracts.CityService;
import com.amr.project.service.abstracts.CountryService;
import com.amr.project.service.abstracts.UserService;
import com.amr.project.webapp.security.SecurityConfig;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Import(SecurityConfig.class)
@Controller("")
public class UserController {

    private final UserService userService;
    private final CountryService countryService;
    private final CityService cityService;
    private final AddressService addressService;
    private final UserMapper mapper;


    public UserController(UserService userService,
                          CountryService countryService, CityService cityService, AddressService addressService,
                          UserMapper mapper) {

        this.userService = userService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.addressService = addressService;
        this.mapper = mapper;

    }

    @PostMapping("/signup")
    public ModelAndView createNewUser(@ModelAttribute User user) {

        if (userService.registerNewUser(user)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("signup");
            modelAndView.addObject("date", new Date());
              modelAndView.addObject("address", new Address());

            return modelAndView;
        } else {

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index");//другая страница или сообщение, что такой пользователь существует
            return modelAndView;
        }
    }

    @PostMapping("/update")
    public String updateDataUser(@ModelAttribute User user,
                                 @ModelAttribute("nameCountry") String name,
                                 @ModelAttribute ("nameCity") String name1,
                                 @ModelAttribute Address address,
                                 @ModelAttribute String date) {

           countryService.addNewCountry(new Country(name));
        cityService.addNewCity(new City(name1,
                countryService.findByName(name)));

        address.setCity(cityService.findByName(name1));
        address.setCountry(countryService.findByName(name));

        addressService.addNewAddress(address);
        address.setId(addressService.getByAddress(address).getId());
        user.addAddress(addressService.findById(address.getId()));
        userService.updateUser(user);
        return "redirect:/";
    }

}

