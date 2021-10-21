package com.amr.project.converter;

import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ShopMapper.class)
public interface CartItemMapper {
    @Mapping(target = "itemDto", source = "item")
    @Mapping(target = "shopDto", source = "shop")
    CartItemDto CartItemToDto(CartItem cartItem);
}
