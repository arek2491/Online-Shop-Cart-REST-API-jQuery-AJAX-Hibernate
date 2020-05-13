package com.online.shop.service;

import com.online.shop.domain.Item;
import com.online.shop.domain.ItemNotFoundException;
import com.online.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDbService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    public Item getItemById(final Long itemId) throws ItemNotFoundException {
        return itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }
}
