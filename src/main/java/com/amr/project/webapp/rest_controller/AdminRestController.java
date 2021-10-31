package com.amr.project.webapp.rest_controller;

import com.amr.project.model.dto.*;
import com.amr.project.model.entity.*;
import com.amr.project.service.abstracts.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private final RoleService roleService;

    public AdminRestController(AdminService adminService, CountryService countryService, CityService cityService,
                               AddressService addressService, CategoryService categoryService, UserService userService,
                               ShopService shopService, ItemService itemService, RoleService roleService) {
        this.adminService = adminService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.addressService = addressService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.shopService = shopService;
        this.itemService = itemService;
        this.roleService = roleService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/adminpagedto")
    public AdminPageDto show() {
        return adminService.show();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/countries")
    public List<CountryDto> showListCountry() {
        return adminService.show().getCountryDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/countries/{id}")
    public Country oneCountry(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/countries")
    public Country saveCountry(@RequestBody Country country) {
        countryService.persist(country);
        return country;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/countries")
    public Country updateCountry(@RequestBody Country country) {
        countryService.update(country);
        return country;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/countries/{id}")
    public String deleteCountry(@PathVariable long id) {
        countryService.delete(countryService.findById(id));
        return "" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/cities")
    public List<AdminCityDto> showListCity() {
        return adminService.show().getCityDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/cities/{id}")
    public City oneCity(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/cities")
    public City saveCity(@RequestBody City city) {
        city.setCountry(countryService.findById(city.getCountryId()));
        cityService.persist(city);
        return city;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/cities")
    public City updateCity(@RequestBody City city) {
        city.setCountry(countryService.findById(city.getCountryId()));
        cityService.update(city);
        return city;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/cities/{id}")
    public String deleteCity(@PathVariable long id) {
        cityService.delete(cityService.findById(id));
        return "" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/addresses")
    public List<AdminAddressDto> showListAddresses() {
        return adminService.show().getAddressDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/addresses/{id}")
    public Address oneAddress(@PathVariable Long id) {
        return addressService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/addresses")
    public Address saveAddress(@RequestBody Address address) {
        address.setCountry(cityService.findById(address.getCityId()).getCountry());
        address.setCity(cityService.findById(address.getCityId()));
        addressService.persist(address);
        return address;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/addresses")
    public Address updateAddress(@RequestBody Address address) {
        address.setCountry(cityService.findById(address.getCityId()).getCountry());
        address.setCity(cityService.findById(address.getCityId()));
        addressService.update(address);
        return address;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/addresses/{id}")
    public String deleteAddress(@PathVariable long id) {
        addressService.delete(addressService.findById(id));
        return "" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/categories")
    public List<CategoryDto> showListCategories() {
        return adminService.show().getCategoryDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/categories/{id}")
    public Category oneCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) {
        categoryService.persist(category);
        return category;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/categories")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return category;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.delete(categoryService.findById(id));
        return "" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<AdminUserDto> showListUsers() {
        return adminService.show().getUserDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/users/{id}")
    public User oneUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.findByName(role.getName()));
        }
        user.setRoles(roles);
        userService.persist(user);
        return user;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/users")
    public User updateUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.findByName(role.getName()));
        }
        user.setRoles(roles);
        userService.update(user);
        return user;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(userService.findById(id));
        return "" + id;
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/shops")
    public List<AdminShopDto> showListShops() {
        return adminService.show().getShopDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/shops/{id}")
    public Shop oneShop(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/shops")
    public Shop saveShop(@RequestBody Shop shop) {
        shop.setLocation(countryService.findById(shop.getCountryId()));
        shop.setUser(userService.findById(shop.getUserId()));
        shopService.persist(shop);
        return shop;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/shops")
    public Shop updateShop(@RequestBody Shop shop) {
        shop.setLocation(countryService.findById(shop.getCountryId()));
        shop.setUser(userService.findById(shop.getUserId()));
        shopService.update(shop);
        return shop;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/shops/{id}")
    public String deleteShop(@PathVariable long id) {
        shopService.delete(shopService.findById(id));
        return "" + id;
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/items")
    public List<AdminItemDto> showListItems() {
        return adminService.show().getItemDtoList();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/items/{id}")
    public Item oneItem(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("/items")
    public Item saveItem(@RequestBody Item item) {
        item.setShop(shopService.findById(item.getShopId()));
        List<Category> categories = new ArrayList<>();
        for (Category category : item.getCategories()) {
            categories.add(categoryService.findByName(category.getName()));
        }
        item.setCategories(categories);
        itemService.persist(item);
        return item;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PatchMapping("/items")
    public Item updateItem(@RequestBody Item item) {
        item.setShop(shopService.findById(item.getShopId()));
        List<Category> categories = new ArrayList<>();
        for (Category category : item.getCategories()) {
            categories.add(categoryService.findByName(category.getName()));
        }
        item.setCategories(categories);
        itemService.update(item);
        return item;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/items/{id}")
    public String deleteItem(@PathVariable long id) {
        itemService.delete(itemService.findById(id));
        return "" + id;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/roles")
    public List<RoleDto> showListRoles() {
        return adminService.show().getRoleDtoList();
    }
}
