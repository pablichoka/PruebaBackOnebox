package com.pruebabackonebox.repository;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pruebabackonebox.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {

  public Optional<Cart> findById(String id);

  public HashSet<Cart> findAll();
}
