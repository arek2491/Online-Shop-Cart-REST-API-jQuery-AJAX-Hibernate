package com.online.shop.mapper;

import com.online.shop.domain.Item;
import com.online.shop.domain.ItemDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemMapperTestSuite {

    private ItemMapper mapper = new ItemMapper();

    @Test
    public void testMapToItem() {
        ItemDto itemDto = new ItemDto(1L, "name 1", 1D, 1D);
        Item item = mapper.mapToItem(itemDto);

        assertEquals(1L, item.getId(), 0);
        assertEquals("name 1", item.getName());
        assertEquals(1D, item.getQuantity(), 0);
        assertEquals(1D, item.getPrice(), 0);
    }

    @Test
    public void testMapToItemDto() {
        Item item = new Item(1L, "name 1", 1D, 1D);
        ItemDto itemDto = mapper.mapToItemDto(item);

        assertEquals(1L, itemDto.getId(), 0);
        assertEquals("name 1", itemDto.getName());
        assertEquals(1D, itemDto.getQuantity(), 0);
        assertEquals(1D, itemDto.getPrice(), 0);
    }
}
