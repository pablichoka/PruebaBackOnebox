package com.pruebabackonebox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabackonebox.dto.AddProductDTO;
import com.pruebabackonebox.dto.CreateProductDTO;
import com.pruebabackonebox.dto.ProductDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Products", description = "Endpoints for products management")
public interface ProductController {

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id);

  @GetMapping("/all")
  @ResponseBody
  public ResponseEntity<Iterable<ProductDTO>> getAllProducts();

  @PostMapping("/add")
  @ResponseBody
  public ResponseEntity<ProductDTO> addProduct(@RequestBody CreateProductDTO product);

  @DeleteMapping("/delete")
  @ResponseBody
  public ResponseEntity<ProductDTO> deleteProduct(@RequestParam Integer id);

  @PutMapping("/update/{id}")
  @ResponseBody
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody CreateProductDTO product);

}
