package com.amr.project.converter;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ShopMapper.class)
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    @Mapping(target = "itemDto", source = "item")
    @Mapping(target = "shopDto", source = "shop")
    CartItemDto CartItemToDto(CartItem cartItem);
}
