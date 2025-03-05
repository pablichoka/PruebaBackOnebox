package com.pruebabackonebox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Product {

  private Integer id;
  private String description;
  private double amount;
  
  public Product() {
    this.id = 0;
    this.description = "";
    this.amount = 0.0;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(id, description, amount);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Product other = (Product) obj;
    return java.util.Objects.equals(id, other.id) &&
           java.util.Objects.equals(description, other.description) &&
           java.util.Objects.equals(amount, other.amount);
  }

}
