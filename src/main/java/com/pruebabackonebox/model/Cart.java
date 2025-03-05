package com.pruebabackonebox.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Cart {

  private String id;
  private Set<CartProduct> cartProducts;
  private LocalDateTime lastUpdated;

  public Cart() {
    this.id = UUID.randomUUID().toString();
    this.cartProducts = new HashSet<>();
    this.lastUpdated = LocalDateTime.now();
  }

  public void updateTimestamp() {
    this.lastUpdated = LocalDateTime.now();
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, lastUpdated);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Cart other = (Cart) obj;
    return java.util.Objects.equals(id, other.id) &&
           java.util.Objects.equals(cartProducts, other.cartProducts) &&
           java.util.Objects.equals(lastUpdated, other.lastUpdated);
  }

}
