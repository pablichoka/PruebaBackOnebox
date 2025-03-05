package com.pruebabackonebox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CartProduct {

  private CartProductId id;
  private Cart cart;
  private Product product;
  private Integer quantity;
  
  public CartProduct() {
    this.id = new CartProductId();
    this.cart = new Cart();
    this.product = new Product();
    this.quantity = 0;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, product, quantity);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CartProduct other = (CartProduct) obj;
    return java.util.Objects.equals(id, other.id) &&
           java.util.Objects.equals(cart, other.cart) &&
           java.util.Objects.equals(product, other.product) &&
           java.util.Objects.equals(quantity, other.quantity);
  }

}
