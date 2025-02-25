package com.pruebabackonebox.controller.impl;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pruebabackonebox.controller.CartController;
import com.pruebabackonebox.dto.AddProductDTO;
import com.pruebabackonebox.dto.ProductCartDTO;
import com.pruebabackonebox.dto.DeleteProductDTO;
import com.pruebabackonebox.service.CartService;

@Service
public class CartControllerImpl implements CartController {

  private final CartService cartService;

  @Autowired
  public CartControllerImpl(CartService cartService) {
    this.cartService = cartService;
  }

  @Override
  public ResponseEntity<List<ProductCartDTO>> getCart(String id) {
    List<ProductCartDTO> cart = cartService.getCartDetails(id);
    if (cart != null) {
      return ResponseEntity.ok(cart);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<String[]> getAllCarts() {
    return ResponseEntity.ok(cartService.getAllCarts());
  }

  @Override
  public ResponseEntity<ProductCartDTO> addProductToCart(String id, AddProductDTO productDTO) {
    ProductCartDTO productCartDTO = cartService.addProductToCart(id, productDTO.getProductId(), productDTO.getQuantity());
    return ResponseEntity.ok(productCartDTO);
  }

  @Override
  public ResponseEntity<Response> deleteProductFromCart(String id, DeleteProductDTO productDTO) {
    cartService.removeProductFromCart(id, productDTO.getProductId());
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Response> clearCart(String id) {
    cartService.clearCart(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Response> deleteCart(String id) {
    cartService.deleteCart(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<String> createCart() {
    return ResponseEntity.ok(cartService.createCart());
  }

}
