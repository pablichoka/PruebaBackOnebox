package com.pruebabackonebox.controller;

import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebabackonebox.dto.AddProductDTO;
import com.pruebabackonebox.dto.DeleteProductDTO;
import com.pruebabackonebox.dto.ProductCartDTO;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@Hidden
@RequestMapping("/cart")
public interface CartController {

  @GetMapping("/{id}")
  ResponseEntity<Set<ProductCartDTO>> getCart(@PathVariable String id);

  @GetMapping("/all")
  ResponseEntity<String[]> getAllCarts();

  @GetMapping("/add")
  ResponseEntity<String> createCart();

  @PostMapping("/add/{id}")
  ResponseEntity<ProductCartDTO> addProductToCart(@PathVariable String cartId, @RequestBody AddProductDTO productData);

  @PostMapping("/delete-product/{id}")
  ResponseEntity<Response> deleteProductFromCart(@PathVariable String id, @RequestBody DeleteProductDTO productData);

  @DeleteMapping("/clear/{id}")
  ResponseEntity<Response> clearCart(@PathVariable String id);

  @DeleteMapping("/delete/{id}")
  ResponseEntity<Response> deleteCart(@PathVariable String id);

  @GetMapping("/total/{id}")
  ResponseEntity<Double> getTotalPrice(@PathVariable String id);

}
