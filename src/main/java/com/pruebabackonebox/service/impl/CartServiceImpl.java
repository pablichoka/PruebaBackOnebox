package com.pruebabackonebox.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.pruebabackonebox.dto.CartDTO;
import com.pruebabackonebox.dto.ProductDTO;
import com.pruebabackonebox.model.Cart;
import com.pruebabackonebox.model.Product;
import com.pruebabackonebox.repository.CartRepository;
import com.pruebabackonebox.service.CartService;
import com.pruebabackonebox.service.ProductService;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final ProductService productService;

  @Autowired
  public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
    this.cartRepository = cartRepository;
    this.productService = productService;
  }

  @Override
  public boolean setProductToCart(String cartId, Integer productId, Integer quantity) {
    boolean productExists = productService.productExists(productId);
    boolean cartExists = cartRepository.existsById(cartId);
    if (productExists) {
      // Get product, update amount and refactor to add to the cart
      Product product = productService.getFullProduct(productId);
      ProductDTO productDTO = new ProductDTO(product.getDescription(), product.getAmount());
      productService.updateProduct(productId, productDTO);
      List<Product> products = new ArrayList<>();
      products.add(product);
      // Get cart and add product to it
      if (cartExists) {
        Cart cart = cartRepository.findById(cartId).get();
        cart.setProducts(products);
        cartRepository.save(cart);
        return true;
      } else {
        Cart cart = new Cart();
        cart.setProducts(products);
        cartRepository.save(cart);
        return true;
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean removeProductFromCart(String cartId, Integer productId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart != null) {
      List<Product> products = cart.getProducts();
      products.removeIf(product -> product.getId().equals(productId));
      cart.setProducts(products);
      cartRepository.save(cart);
      return true;
    } else {
      return false;
    }

  }

  @Override
  public List<CartDTO> getCartDetails(String cartId) {
    boolean cart = cartRepository.existsById(cartId);
    if (cart != false) {
      List<CartDTO> products = new ArrayList<>();
      for (Product product : cartRepository.findById(cartId).get().getProducts()) {
        CartDTO tempCartDTO = new CartDTO(product.getId(), product.getAmount());
        products.add(tempCartDTO);
      };
      return products;
    } else {
      return null;
    }
  }

  @Override
  public void clearCart(String cartId) {
    cartRepository.deleteById(cartId);
  }

  @Override
  public String[] getAllCarts() {
    List<Cart> carts = cartRepository.findAll();
    String[] cartIds = new String[carts.size()];
    for (int i = 0; i < carts.size(); i++) {
      cartIds[i] = carts.get(i).getId();
    }
    return cartIds;
  }

  @Override
  public void deleteCart(String cartId) {
    cartRepository.deleteById(cartId);
  }

  @Override
  public String createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    return cart.getId();
  }

}
