package com.amr.project.converter;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.ItemDto;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.dto.ShopDto;
import com.amr.project.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPageMapper {

    @Mapping(source = "shop.name", target = "shopName")
    @Mapping(source = "shop.logo", target = "shopLogo")
    @Mapping(source = "shop.phone", target = "shopPhone")
    @Mapping(source = "shop.location", target = "shopLocation")
    ItemDto itemToItemDto(Item item);

    @Mapping(source = "user.firstName", target = "userFirstName")
    @Mapping(source = "user.lastName", target = "userLastName")
    @Mapping(source = "user.images", target = "userImages")
    ReviewDto reviewToReviewDto(Review review);

    List<CategoryDto> categoriesToCategoriesDto(List<Category> categories);

}
