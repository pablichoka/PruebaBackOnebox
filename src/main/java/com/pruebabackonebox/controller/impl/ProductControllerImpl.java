package com.pruebabackonebox.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pruebabackonebox.controller.ProductController;
import com.pruebabackonebox.dto.CreateProductDTO;
import com.pruebabackonebox.dto.ProductDTO;
import com.pruebabackonebox.service.ProductService;

@Service
public class ProductControllerImpl implements ProductController {

  private final ProductService productService;

  @Autowired
  public ProductControllerImpl(ProductService productService) {
    this.productService = productService;
  }

  @Override
  public ResponseEntity<ProductDTO> getProduct(Integer id) {
    ProductDTO product = productService.getProductInformation(id);
    if (product != null) {
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<ProductDTO> addProduct(CreateProductDTO product) {
    return ResponseEntity.ok(productService.addProduct(product));
  }

  @Override
  public ResponseEntity<ProductDTO> deleteProduct(Integer id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Iterable<ProductDTO>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @Override
  public ResponseEntity<ProductDTO> updateProduct(Integer id, ProductDTO product) {
    return ResponseEntity.ok(productService.updateProduct(id, product));
  }
}
