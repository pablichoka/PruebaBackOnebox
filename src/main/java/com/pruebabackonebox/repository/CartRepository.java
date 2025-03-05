package com.pruebabackonebox.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.pruebabackonebox.model.Cart;

@Repository
public class CartRepository {

  private final Map<String, Cart> carts = new HashMap<>();

  public Cart save(Cart cart) {
    if (cart.getId() == null || cart.getId().isEmpty()) {
      cart.setId(UUID.randomUUID().toString());
    }
    carts.put(cart.getId(), cart);
    return cart;
  }

  public Cart findById(String id) {
    Optional<Cart> cartAux = Optional.ofNullable(carts.get(id));
    if(cartAux.isPresent()) {
      return cartAux.get();
    } else {
      return null;
    }
  }

  public boolean existsById(String id) {
    return carts.containsKey(id);
  }

  public HashSet<Cart> findAll() {
    return new HashSet<>(carts.values());
  }

  public void deleteById(String id) {
    carts.remove(id);
  }

  public void delete(Cart cart) {
    carts.remove(cart.getId());
  }

  public void clear(Cart cart){
    cart.getCartProducts().clear();
    cart.updateTimestamp();
  }
}
