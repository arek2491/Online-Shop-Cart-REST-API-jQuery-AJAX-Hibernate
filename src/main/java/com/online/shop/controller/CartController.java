package com.online.shop.controller;

import com.online.shop.domain.CartDto;
import com.online.shop.domain.ItemNotFoundException;
import com.online.shop.mapper.ItemMapper;
import com.online.shop.service.CartDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class CartController {

    @Autowired
    private CartDbService cartDbService;
    @Autowired
    private ItemMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/cart")
    public List<CartDto> getCart() {
        return mapper.mapToCartDtoList(cartDbService.getCart());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cart/{itemId}")
    public CartDto getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return mapper.mapToCartDto(cartDbService.getCartById(itemId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cart/{cartId}")
    public void deleteItemFromCart(@PathVariable Long cartId) {
        cartDbService.deleteItem(cartId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cart", consumes = APPLICATION_JSON_VALUE)
    public void saveItemToCart(@RequestBody CartDto cartDto) {
        cartDbService.saveCart(mapper.mapToCartFromCartDto(cartDto));
    }

}
