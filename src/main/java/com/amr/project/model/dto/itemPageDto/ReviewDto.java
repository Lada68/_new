package com.amr.project.model.dto.itemPageDto;

import com.amr.project.model.dto.ImageDto;
import lombok.*;

@Data
public class ReviewDto {
    private Long id;
    private String dignity; //плюсы
    private String flaw; //минусы
    private String text;
    private int rating;
    private String date;
    private Long itemId;
    private String itemName;
    private UserDto user;
    private Long shopId;
    private String shopName;
    private boolean isModerated;
    private boolean isModerateAccept;
    private String moderatedRejectReason;

}
