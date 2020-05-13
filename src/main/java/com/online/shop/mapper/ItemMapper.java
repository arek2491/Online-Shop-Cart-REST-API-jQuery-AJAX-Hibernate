package com.online.shop.mapper;

import com.online.shop.domain.Cart;
import com.online.shop.domain.CartDto;
import com.online.shop.domain.Item;
import com.online.shop.domain.ItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(item.getId(), item.getName(), item.getQuantity(), item.getPrice());
    }

    public Item mapToItem(final ItemDto itemDto) {
        return new Item(itemDto.getId(), itemDto.getName(), itemDto.getQuantity(), itemDto.getPrice());
    }

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(cart.getId(), cart.getName(), cart.getQuantity(), cart.getPrice());
    }

    public List<ItemDto> mapToItemDtoList(final List<Item> items) {
        return items.stream()
                .map(i -> new ItemDto(i.getId(), i.getName(), i.getQuantity(), i.getPrice()))
                .collect(Collectors.toList());
    }


    public Cart mapToCartFromCartDto(final CartDto cartDto) {
        return new Cart(cartDto.getId(), cartDto.getName(), cartDto.getQuantity(), cartDto.getPrice());
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> items) {
        return items.stream()
                .map(i -> new CartDto(i.getId(), i.getName(), i.getQuantity(), i.getPrice()))
                .collect(Collectors.toList());
    }
}
