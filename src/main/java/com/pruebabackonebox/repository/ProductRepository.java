package com.pruebabackonebox.repository;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pruebabackonebox.model.Product;

@Component
public class ProductRepository {

  private final HashSet<Product> products = new HashSet<>();

  public Product save(Product product) {
    if (product.getId() != null) {
      products.removeIf(p -> p.getId().equals(product.getId()));
    }
    products.add(product);
    return product;
  }

  public Optional<Product> findById(Integer id) {
    return products.stream()
        .filter(p -> p.getId().equals(id))
        .findFirst();
  }

  public Optional<Product> findByDescription(String description) {
    return products.stream()
        .filter(p -> description.equals(p.getDescription()))
        .findFirst();
  }

  public HashSet<Product> findAll() {
    return new HashSet<>(products);
  }

  public boolean existsById(Integer id) {
    return products.stream().anyMatch(p -> p.getId().equals(id));
  }

  public void deleteById(Integer id) {
    products.removeIf(p -> p.getId().equals(id));
  }

  public void delete(Product entity) {
    products.remove(entity);
  }
}
