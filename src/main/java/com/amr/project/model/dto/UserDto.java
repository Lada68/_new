package com.amr.project.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private List<AddressDto> address;
    private List<ImageDto> images;
    private Calendar birthday;
    private List<OrderDto> orders = new ArrayList<>();
    private List<ShopDto> shops = new ArrayList<>();





}
