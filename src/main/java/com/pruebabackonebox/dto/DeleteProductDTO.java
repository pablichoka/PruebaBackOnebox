package com.pruebabackonebox.dto;

public class DeleteProductDTO {
    private Integer productId;

    public DeleteProductDTO() {
    }

    public DeleteProductDTO(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
