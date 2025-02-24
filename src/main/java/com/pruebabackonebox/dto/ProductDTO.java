package com.pruebabackonebox.dto;

import com.pruebabackonebox.model.Product;

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

    public ProductDTO() {
    }

    public ProductDTO(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
