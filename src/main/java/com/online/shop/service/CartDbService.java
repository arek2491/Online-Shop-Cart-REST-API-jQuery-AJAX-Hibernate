package com.online.shop.service;

import com.online.shop.domain.Cart;
import com.online.shop.domain.ItemNotFoundException;
import com.online.shop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDbService {

    @Autowired
    private CartRepository repository;

    public List<Cart> getCart() {
        return repository.findAll();
    }

    public Cart getCartById(final Long cartId) throws ItemNotFoundException {
        return repository.findById(cartId).orElseThrow(ItemNotFoundException::new);
    }

    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    public void deleteItem(final Long cartId) {
        repository.deleteById(cartId);
    }
}
