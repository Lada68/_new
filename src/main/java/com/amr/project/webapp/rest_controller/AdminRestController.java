package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.*;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminapi")
public class AdminRestController {
    private final AdminService adminService;
    private final CountryService countryService;
    private final CityService cityService;
    private final AddressService addressService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ShopService shopService;
    private final ItemService itemService;

    public AdminRestController(AdminService adminService, CountryService countryService, CityService cityService,
                               AddressService addressService, CategoryService categoryService, UserService userService,
                               ShopService shopService, ItemService itemService) {
        this.adminService = adminService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.addressService = addressService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping("/adminpagedto")
    public AdminPageDto show() {
        return adminService.show();
    }

    @GetMapping("/countries")
    public List<CountryDto> showListCountry() {
        return adminService.show().getCountryDtoList();
    }

    @GetMapping("/countries/{id}")
    public Country oneCountry(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PostMapping("/countries")
    public Country saveCountry(@RequestBody Country country) {
        countryService.persist(country);
        return country;
    }

    @PatchMapping("/countries")
    public Country updateCountry(@RequestBody Country country) {
        countryService.update(country);
        return country;
    }

    @DeleteMapping("/countries/{id}")
    public String deleteCountry(@PathVariable long id) {
        countryService.delete(countryService.findById(id));
        return "" + id;
    }

    @GetMapping("/cities")
    public List<AdminCityDto> showListCity() {
        return adminService.show().getCityDtoList();
    }

    @GetMapping("/cities/{id}")
    public City oneCity(@PathVariable Long id) {
        return cityService.findById(id);
    }


    @PostMapping("/cities")
    public City saveCity(@RequestBody City city) {
        city.setCountry(countryService.findById(city.getCountryId()));
        cityService.persist(city);
        return city;
    }

    @PatchMapping("/cities")
    public City updateCity(@RequestBody City city) {
        city.setCountry(countryService.findById(city.getCountryId()));
        cityService.update(city);
        return city;
    }

    @DeleteMapping("/cities/{id}")
    public String deleteCity(@PathVariable long id) {
        cityService.delete(cityService.findById(id));
        return "" + id;
    }

    @GetMapping("/addresses")
    public List<AdminAddressDto> showListAddresses() {
        return adminService.show().getAddressDtoList();
    }

    @GetMapping("/addresses/{id}")
    public Address oneAddress(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PostMapping("/addresses")
    public Address saveAddress(@RequestBody Address address) {
        address.setCountry(cityService.findById(address.getCityId()).getCountry());
        address.setCity(cityService.findById(address.getCityId()));
        addressService.persist(address);
        return address;
    }

    @PatchMapping("/addresses")
    public Address updateAddress(@RequestBody Address address) {
        address.setCountry(cityService.findById(address.getCityId()).getCountry());
        address.setCity(cityService.findById(address.getCityId()));
        addressService.update(address);
        return address;
    }

    @DeleteMapping("/addresses/{id}")
    public String deleteAddress(@PathVariable long id) {
        addressService.delete(addressService.findById(id));
        return "" + id;
    }

    @GetMapping("/categories")
    public List<CategoryDto> showListCategories() {
        return adminService.show().getCategoryDtoList();
    }

    @GetMapping("/categories/{id}")
    public Category oneCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) {
        categoryService.persist(category);
        return category;
    }

    @PatchMapping("/categories")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return category;
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.delete(categoryService.findById(id));
        return "" + id;
    }

    @GetMapping("/users")
    public List<AdminUserDto> showListUsers() {
        return adminService.show().getUserDtoList();
    }

    @GetMapping("/users/{id}")
    public User oneUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        userService.persist(user);
        return user;
    }

    @PatchMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(userService.findById(id));
        return "" + id;
    }





    @GetMapping("/shops")
    public List<AdminShopDto> showListShops() {
        return adminService.show().getShopDtoList();
    }

    @GetMapping("/shops/{id}")
    public Shop oneShop(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shops")
    public Shop saveShop(@RequestBody Shop shop) {
        shopService.persist(shop);
        return shop;
    }

    @PatchMapping("/shops")
    public Shop updateShop(@RequestBody Shop shop) {
        shopService.update(shop);
        return shop;
    }

    @DeleteMapping("/shops/{id}")
    public String deleteShop(@PathVariable long id) {
        shopService.delete(shopService.findById(id));
        return "" + id;
    }





    @GetMapping("/items")
    public List<AdminItemDto> showListItems() {
        return adminService.show().getItemDtoList();
    }

    @GetMapping("/items/{id}")
    public Item oneItem(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PostMapping("/items")
    public Item saveItem(@RequestBody Item item) {
        itemService.persist(item);
        return item;
    }

    @PatchMapping("/items")
    public Item updateItem(@RequestBody Item item) {
        itemService.update(item);
        return item;
    }

    @DeleteMapping("/items/{id}")
    public String deleteItem(@PathVariable long id) {
        itemService.delete(itemService.findById(id));
        return "" + id;
    }
}
