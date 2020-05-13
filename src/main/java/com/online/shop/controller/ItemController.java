package com.online.shop.controller;

import com.online.shop.domain.ItemDto;
import com.online.shop.domain.ItemNotFoundException;
import com.online.shop.mapper.ItemMapper;
import com.online.shop.service.ItemDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
public class ItemController {

    @Autowired
    private ItemDbService itemDbService;

    @Autowired
    private ItemMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/items")
    public List<ItemDto> getItems() {
        return mapper.mapToItemDtoList(itemDbService.getAllItems());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/items/itemId")
    public ItemDto getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return mapper.mapToItemDto(itemDbService.getItemById(itemId));
    }
}