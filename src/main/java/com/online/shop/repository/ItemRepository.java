package com.online.shop.repository;

import com.online.shop.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Override
    List<Item> findAll();

    @Override
    Optional<Item> findById(Long itemId);

    @Override
    long count();
}
