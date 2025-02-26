package com.pruebabackonebox.repository;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.pruebabackonebox.model.Product;

@Repository
@RepositoryRestResource(exported = false)
public interface ProductRepository extends CrudRepository<Product, Integer> {

  public Optional<Product> findByDescription(String description);

  public HashSet<Product> findAll();

  public boolean existsById(Integer id);

}
