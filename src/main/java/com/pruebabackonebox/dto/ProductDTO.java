package com.pruebabackonebox.dto;

import com.pruebabackonebox.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String description;
    private Double amount;

    public ProductDTO(Product product) {
      if (product == null) {
          throw new IllegalArgumentException("Product cannot be null");
      }
      if (product.getDescription() == null || product.getDescription().isEmpty()) {
          throw new IllegalArgumentException("Product description cannot be null or empty");
      }
      if (product.getAmount() <= 0) {
          throw new IllegalArgumentException("Product amount must be a positive value");
      }
      this.description = product.getDescription();
      this.amount = product.getAmount();
  }
}
