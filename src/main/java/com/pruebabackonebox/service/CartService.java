package com.pruebabackonebox.service;

import java.util.List;

import com.pruebabackonebox.dto.ProductCartDTO;

public interface CartService {

  ProductCartDTO addProductToCart(String cartId, Integer productId, Double quantity);

  boolean removeProductFromCart(String cartId, Integer productId);

  void clearCart(String cartId);

  void deleteCart(String cartId);

  List<ProductCartDTO> getCartDetails(String cartId);

  String createCart();

  String[] getAllCarts();
}
