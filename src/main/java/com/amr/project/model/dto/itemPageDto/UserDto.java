package com.amr.project.model.dto.itemPageDto;

import com.amr.project.model.dto.ImageDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private List<ImageDto> images;

    public ImageDto getMainImage() {
        return images.stream().filter(ImageDto::getIsMain).findAny().orElse(null);
    }

}
