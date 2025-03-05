package com.pruebabackonebox.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartProductId implements Serializable {

  private String cartId;
  private Integer productId;

  public CartProductId() {
    this.cartId = "";
    this.productId = 0;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(cartId, productId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    CartProductId other = (CartProductId) obj;
    return java.util.Objects.equals(cartId, other.cartId) &&
           java.util.Objects.equals(productId, other.productId);
  }

}