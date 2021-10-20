package com.amr.project.converter;

import com.amr.project.model.dto.CategoryDto;
import com.amr.project.model.dto.CountryDto;
import com.amr.project.model.dto.itemPageDto.ItemDto;
import com.amr.project.model.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemPageMapper {

    ItemDto itemToItemDto(Item item);

    List<CategoryDto> categoriesToCategoriesDto(List<Category> categories);

    CountryDto countryToCountryDto(Country country);

}
