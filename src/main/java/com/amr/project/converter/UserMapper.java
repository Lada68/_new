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

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = ".")
    UserDto toDto(User user);

    User toModel(UserDto userDto);

    @Mapping(target = "city", source = "city.name")
    @Mapping(target = "country", source = "country.name")
    AddressDto addressToAddressDto(Address address);
    ImageDto imageToImageDto(Image image);
    OrderDto orderToOrderDto(Order order);

    default String getUserName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}