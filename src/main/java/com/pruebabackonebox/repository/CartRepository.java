package com.pruebabackonebox.repository;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.pruebabackonebox.model.Cart;

@Repository
@RepositoryRestResource(exported = false)
public interface CartRepository extends CrudRepository<Cart, String> {

  public Optional<Cart> findById(String id);

  public HashSet<Cart> findAll();
}
