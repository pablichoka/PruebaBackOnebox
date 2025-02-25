package com.pruebabackonebox.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_product")
public class CartProduct {

  @EmbeddedId
  private CartProductId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Cart cart;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Product product;

  private Integer quantity;

  public CartProductId getId() {
    return id;
  }

  public void setId(CartProductId id) {
    this.id = id;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "CartProduct [id=" + id + ", cart=" + cart + ", product=" + product + ", quantity=" + quantity + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof CartProduct))
      return false;
    CartProduct other = (CartProduct) obj;
    return id.equals(other.id) && cart.equals(other.cart) && product.equals(other.product)
        && quantity.equals(other.quantity);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}