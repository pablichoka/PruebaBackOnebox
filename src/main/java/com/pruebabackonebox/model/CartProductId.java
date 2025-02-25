package com.pruebabackonebox.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CartProductId implements Serializable {

  @Column(name = "cart_id")
  private String cartId;
  @Column(name = "product_id")
  private Integer productId;

  public CartProductId() {
  }

  public CartProductId(String cartId, Integer productId) {
    this.cartId = cartId;
    this.productId = productId;
  }

  public String getCartId() {
    return cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof CartProductId))
      return false;
    CartProductId that = (CartProductId) o;
    return Objects.equals(cartId, that.cartId) && Objects.equals(productId, that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cartId, productId);
  }

  @Override
  public String toString() {
    return "CartProductId{" +
        "cartId='" + cartId + '\'' +
        ", productId=" + productId +
        '}';
  }
}