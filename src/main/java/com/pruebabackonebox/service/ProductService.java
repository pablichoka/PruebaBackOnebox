package com.pruebabackonebox.service;

import com.pruebabackonebox.dto.ProductDTO;
import com.pruebabackonebox.model.Product;

public interface ProductService {

  Product getFullProduct(Integer id);

  ProductDTO getProductInformation(Integer id);

  Iterable<ProductDTO> getAllProducts();

  boolean productExists(Integer id);

  ProductDTO addProduct(ProductDTO product);

  ProductDTO updateProduct(Integer id, ProductDTO productDTO);

  void deleteProduct(Integer id);
}
