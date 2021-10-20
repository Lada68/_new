package com.amr.project.model.dto.itemPageDto;

import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.ImageDto;
import lombok.Data;

@Data
public class ShopDto {
    private Long id;
    private String name;
    private String phone;
    private ImageDto logo;
    private double rating;
    private CountryDto location;
}
