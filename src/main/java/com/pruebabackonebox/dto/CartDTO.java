package com.pruebabackonebox.dto;

public class CartDTO {

  private Integer productId;
  private Double quantity;

  public CartDTO() {
  }

  public CartDTO(Integer productId, Double quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
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
    if (!(obj instanceof CartDTO))
      return false;
    CartDTO other = (CartDTO) obj;
    return productId == other.productId && quantity == other.quantity;
  }

}