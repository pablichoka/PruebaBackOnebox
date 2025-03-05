package com.pruebabackonebox.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.ArrayList;

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

  public Iterable<Product> saveAll(Iterable<Product> product) {
    product.forEach(this::save);
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

  public Iterable<Product> findAllById(Iterable<Integer> ids) {
    ArrayList<Product> result = new ArrayList<>();
    ids.forEach(id -> findById(id).ifPresent(result::add));
    return result;
  }

  public long count() {
    return products.size();
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

  public void deleteAll(Iterable<? extends Product> entities) {
    entities.forEach(this::delete);
  }

  public void deleteAll() {
    products.clear();
  }
}
