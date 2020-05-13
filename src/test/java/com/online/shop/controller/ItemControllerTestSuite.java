package com.online.shop.controller;

import com.online.shop.domain.Item;
import com.online.shop.domain.ItemDto;
import com.online.shop.mapper.ItemMapper;
import com.online.shop.service.ItemDbService;
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
@WebMvcTest(ItemController.class)
public class ItemControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemDbService service;
    @MockBean
    private ItemMapper mapper;

    @Test
    public void testGetItem() throws Exception {
        List<ItemDto> testItemDtoList = new ArrayList<>();
        List<Item> testItemList = new ArrayList<>();
        testItemDtoList.add(new ItemDto(1L, "test name", 1D, 1D));
        testItemList.add(new Item(1L, "test name", 1D, 1D));

        when(service.getAllItems()).thenReturn(testItemList);
        when(mapper.mapToItemDtoList(testItemList)).thenReturn(testItemDtoList);

        mockMvc.perform(get("/v1/items").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("test name")))
                .andExpect(jsonPath("$[0].quantity", is(1D)))
                .andExpect(jsonPath("$[0].price", is(1D)));
    }
}
