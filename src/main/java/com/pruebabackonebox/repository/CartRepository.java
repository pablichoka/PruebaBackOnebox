package com.pruebabackonebox.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pruebabackonebox.model.Cart;
import com.pruebabackonebox.model.Product;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

  public Optional<Cart> findById(String id);

  public List<Cart> findAll();

  public List<Cart> findAllByProducts(Product product);

}
