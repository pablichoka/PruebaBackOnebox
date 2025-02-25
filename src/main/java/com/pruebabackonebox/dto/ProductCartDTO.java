package com.pruebabackonebox.dto;

public class ProductCartDTO {

  private Integer productId;
  private Integer quantity;
  private double amount;

  public ProductCartDTO() {
  }

  public ProductCartDTO(Integer productId, Integer quantity, double amount) {
    this.productId = productId;
    this.quantity = quantity;
    this.amount = amount;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "CartDTO [productId=" + productId + ", quantity=" + quantity + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof ProductCartDTO))
      return false;
    ProductCartDTO other = (ProductCartDTO) obj;
    return productId == other.productId && quantity == other.quantity;
  }

}