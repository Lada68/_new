package com.amr.project.model.dto.itemPageDto;

import com.amr.project.model.dto.ImageDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private Double rating;
    private String description;
    private Integer discount;

    private ShopDto shop;
    private List<ImageDto> images;

/*
    private List<ReviewDto> reviews;
*/


}
