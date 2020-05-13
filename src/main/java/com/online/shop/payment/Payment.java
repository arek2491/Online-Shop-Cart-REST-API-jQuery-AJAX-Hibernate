package com.online.shop.payment;

import com.online.shop.domain.CartDto;
import com.online.shop.mapper.ItemMapper;
import com.online.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Payment {

    @Autowired
    private CartRepository repository;
    @Autowired
    private ItemMapper mapper;

    public double getPrice() {
        List<CartDto> itemsInCart = mapper.mapToCartDtoList(repository.findAll());
        List<Double> price = itemsInCart.stream()
                .map(i -> i.getPrice() * i.getQuantity())
                .collect(Collectors.toList());
        return price.stream().mapToDouble(Double::doubleValue).sum();
    }
}
