package com.amr.project.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CartItemDto {
    private Long id;
    private ItemDto itemDto;
    private ShopDto shopDto;
    private int quantity;
}