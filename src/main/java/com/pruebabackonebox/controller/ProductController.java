package com.pruebabackonebox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabackonebox.dto.ProductDTO;

@RestController
@RequestMapping("/product")
public interface ProductController {

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id);

  @GetMapping("/all")
  @ResponseBody
  public ResponseEntity<Iterable<ProductDTO>> getAllProducts();

  @PostMapping("/add")
  @ResponseBody
  public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product);

  @DeleteMapping("/delete")
  @ResponseBody
  public ResponseEntity<ProductDTO> deleteProduct(@RequestParam Integer id);

}
