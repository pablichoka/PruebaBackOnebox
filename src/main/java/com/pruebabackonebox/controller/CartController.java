package com.pruebabackonebox.controller;

import java.util.List;

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
import com.pruebabackonebox.dto.CartDTO;
import com.pruebabackonebox.dto.DeleteProductDTO;

@RestController
@RequestMapping("/cart")
public interface CartController {

  @GetMapping("/{id}")
  ResponseEntity<List<CartDTO>> getCart(@PathVariable String id);

  @GetMapping("/all")
  ResponseEntity<String[]> getAllCarts();

  @GetMapping("/add")
  ResponseEntity<String> createCart();

  @PostMapping("/add/{id}")
  ResponseEntity<Response> addProductToCart(@PathVariable String cartId,  @RequestBody AddProductDTO productData);
  
  @PostMapping("/delete-product/{id}")
  ResponseEntity<Response> deleteProductFromCart(@PathVariable String id, @RequestBody DeleteProductDTO productData);

  @DeleteMapping("/clear/{id}")
  ResponseEntity<Response> clearCart(@PathVariable String id);

  @DeleteMapping("/delete/{id}")
  ResponseEntity<Response> deleteCart(@PathVariable String id);

}
