package com.pruebabackonebox.service;

import java.util.Set;

import com.pruebabackonebox.dto.ProductCartDTO;

public interface CartService {

  ProductCartDTO addProductToCart(String cartId, Integer productId, Integer quantity);

  boolean removeProductFromCart(String cartId, Integer productId);

  void clearCart(String cartId);

  void deleteCart(String cartId);

  Set<ProductCartDTO> getCartDetails(String cartId);

  String createCart();

  String[] getAllCarts();

  Double getTotalPrice(String cartId);

  void startTimer(String cartId);

  void resetTimer(String cartId);
}
