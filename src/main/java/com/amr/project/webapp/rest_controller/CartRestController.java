package com.amr.project.webapp.rest_controller;

import com.amr.project.converter.CartItemMapper;
import com.amr.project.model.dto.CartItemDto;
import com.amr.project.model.entity.CartItem;
import com.amr.project.model.entity.Item;
import com.amr.project.service.impl.SimpleRWService;
import com.github.openjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CartRestController {
    private SimpleRWService<Item, Long> itemService;

    private CartItemMapper cartItemMapper;

    public CartRestController(SimpleRWService<Item, Long> itemService, CartItemMapper cartItemMapper) {
        this.itemService = itemService;
        this.cartItemMapper = cartItemMapper;
    }

    @GetMapping("/cart")
    public List<CartItemDto> getCart(HttpSession session) {
        List<CartItemDto> cart;
        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        } else {
            cart = (List<CartItemDto>) session.getAttribute("cart");
        }
        return cart;
    }

    @PutMapping("/cart")
    public List<CartItemDto> updateCart(@RequestBody String data, HttpSession session) {
        List<CartItemDto> cart = getCart(session);
        JSONObject params = new JSONObject(data);
        itemService.getByKey(Item.class, params.getLong("id")).map(item -> {
            CartItemDto cartItem = convert(item);
            cartItem.setQuantity(params.getInt("pre_amount"));
            int index = cart.indexOf(cartItem);
            if (index == -1) {
                cartItem.setQuantity(params.getInt("amount"));
                cart.add(cartItem);
            } else {
                cart.get(index).setQuantity(params.getInt("amount"));
            }
            return cart;
        });
        setCart(session, cart);
        return cart;
    }

    @DeleteMapping("/cart")
    public List<CartItemDto> deleteItem(@RequestBody String data, HttpSession session) {
        List<CartItemDto> cart = getCart(session);
        JSONObject params = new JSONObject(data);
        itemService.getByKey(Item.class, params.getLong("id")).map(item -> {
            CartItemDto cartItem = convert(item);
            cartItem.setQuantity(params.getInt("amount"));
            int index = cart.indexOf(cartItem);
            if (index > -1) {
                cart.remove(index);
            }
            return item;
        });
        setCart(session, cart);
        return cart;
    }

    private void setCart(HttpSession session, List<CartItemDto> cart) {
        session.setAttribute("cart", cart);
    }

    private CartItemDto convert(Item item) {
        return cartItemMapper.CartItemToDto(new CartItem(item.getId(), item, item.getShop(), null, 1));
    }
}
