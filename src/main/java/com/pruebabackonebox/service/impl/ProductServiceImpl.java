package com.pruebabackonebox.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebabackonebox.dto.ProductDTO;
import com.pruebabackonebox.model.Product;
import com.pruebabackonebox.repository.ProductRepository;
import com.pruebabackonebox.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product getFullProduct(Integer id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isPresent()) {
      return product.get();
    } else {
      throw new IllegalArgumentException("Product not found with id: " + id);
    }
  }

  @Override
  public ProductDTO getProductInformation(Integer id) {
    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      return null;
    }
    ProductDTO productDTO = new ProductDTO(product);
    return productDTO;
  }

  @Override
  @Transactional
  public ProductDTO addProduct(ProductDTO productDTO) {
    if (productDTO == null) {
      throw new IllegalArgumentException("ProductDTO cannot be null");
    }
    Product product = new Product();
    product.setDescription(productDTO.getDescription());
    product.setAmount(productDTO.getAmount());
    productRepository.save(product);
    return new ProductDTO(product);
  }

  @Override
  @Transactional
  public void deleteProduct(Integer id) {
    if (!productRepository.existsById(id)) {
      throw new IllegalArgumentException("Product not found with id: " + id);
    }
    productRepository.deleteById(id);
  }

  @Override
  public Iterable<ProductDTO> getAllProducts() {
    Iterable<Product> products = productRepository.findAll();
    Set<ProductDTO> productDTOs = new HashSet<>();
    for (Product product : products) {
      productDTOs.add(new ProductDTO(product));
    }
    return productDTOs;
  }

  @Override
  public boolean productExists(Integer id) {
    return productRepository.existsById(id);
  }

  @Override
  @Transactional
  public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
    if (!productRepository.existsById(id)) {
      throw new IllegalArgumentException("Product not found with id: " + id);
    }
    Product product = productRepository.findById(id).get();
    product.setDescription(productDTO.getDescription());
    product.setAmount(productDTO.getAmount());
    productRepository.save(product);
    return new ProductDTO(product);
  }
}
