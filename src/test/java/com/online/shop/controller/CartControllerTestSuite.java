package com.online.shop.controller;

import com.online.shop.domain.Cart;
import com.online.shop.domain.CartDto;
import com.online.shop.mapper.ItemMapper;
import com.online.shop.service.CartDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartDbService service;
    @MockBean
    private ItemMapper mapper;

    @Test
    public void testGetCart() throws Exception {
        List<CartDto> testCartDtoList = new ArrayList<>();
        List<Cart> testCartList = new ArrayList<>();
        testCartDtoList.add(new CartDto(1L, "test name", 1D, 1D));
        testCartList.add(new Cart(1L, "test name", 1D, 1D));

        when(service.getCart()).thenReturn(testCartList);
        when(mapper.mapToCartDtoList(testCartList)).thenReturn(testCartDtoList);

        mockMvc.perform(get("/v1/cart").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("test name")))
                .andExpect(jsonPath("$[0].quantity", is(1D)))
                .andExpect(jsonPath("$[0].price", is(1D)));
    }
}
