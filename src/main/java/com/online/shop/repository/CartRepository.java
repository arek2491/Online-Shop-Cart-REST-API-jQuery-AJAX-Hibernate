package com.online.shop.repository;

import com.online.shop.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

     @Override
     List<Cart> findAll();

     @Override
     Optional<Cart> findById(Long cartId);

     @Override
     Cart save(Cart cart);

     @Override
     void deleteById(Long cartId);

     @Override
     long count();

}
