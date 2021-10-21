package com.amr.project.model.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String cityIndex;
    private String street;
    private String house;
    private String city;
    private String country;


}
