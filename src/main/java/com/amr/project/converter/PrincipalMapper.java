package com.amr.project.converter;

import com.amr.project.model.dto.AddressDto;
import com.amr.project.model.dto.ImageDto;
import com.amr.project.model.dto.PrincipalDto;
import com.amr.project.model.entity.Address;
import com.amr.project.model.entity.Image;
import com.amr.project.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface PrincipalMapper {

    @Mapping(target = "username", source = ".")
    PrincipalDto userToPrincipalDto(User user);
    @Mapping(target = "city", source = "city.name")
    @Mapping(target = "country", source = "country.name")
    AddressDto addressToAddressDto(Address address);
    ImageDto imageToImageDto(Image image);
    default String getUserName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
    default AddressDto addresslistToAddressDto(List<Address> addresses) {
        return addressToAddressDto(addresses.get(0));
    }
    default ImageDto imagelistToImageDto(List<Image> images) {
        return imageToImageDto(images.get(0));
    }
}
