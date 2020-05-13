package com.online.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartDto {
    private Long id;
    private String name;
    private Double quantity;
    private Double price;


}
