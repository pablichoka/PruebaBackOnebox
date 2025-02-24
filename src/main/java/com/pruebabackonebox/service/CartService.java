package com.pruebabackonebox.service;

import java.util.List;

import com.pruebabackonebox.dto.CartDTO;

public interface CartService {

  boolean setProductToCart(String cartId, Integer productId, Integer quantity);

  boolean removeProductFromCart(String cartId, Integer productId);

  void clearCart(String cartId);

  void deleteCart(String cartId);

  List<CartDTO> getCartDetails(String cartId);

  String createCart();

  String[] getAllCarts();
}
