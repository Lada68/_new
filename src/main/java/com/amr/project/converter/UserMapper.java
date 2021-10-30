package com.amr.project.converter;

import com.amr.project.model.dto.OrderDto;
import com.amr.project.model.dto.UserDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.Order;
import com.amr.project.model.entity.User;
import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {OrderMapper.class,
                AddressMapper.class})
public interface UserMapper {
    UserDto toDto(User user);
    User toModel(UserDto userDto);
}