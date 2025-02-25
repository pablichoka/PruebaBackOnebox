package com.pruebabackonebox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebabackonebox.dto.ProductCartDTO;
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
  public ProductCartDTO addProductToCart(String cartId, Integer productId, Double quantity) {
    boolean productExists = productService.productExists(productId);
    boolean cartExists = cartRepository.existsById(cartId);
    if (productExists && cartExists) {
      // Get product, update amount and refactor to add to the cart
      Product product = productService.getFullProduct(productId);
      ProductDTO productDTO = new ProductDTO(product.getDescription(), quantity);
      productService.updateProduct(productId, productDTO);
      Cart cart = cartRepository.findById(cartId).get();
      List<Product> products = cart.getProducts();
      products.add(product);
      cart.setProducts(products);
      cartRepository.save(cart);
      return new ProductCartDTO(productId, product.getAmount());
    } else {
      return null;
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
  public List<ProductCartDTO> getCartDetails(String cartId) {
    boolean cartExists = cartRepository.existsById(cartId);
    if (cartExists != false) {
      Cart cart = cartRepository.findById(cartId).get();
      List<Product> products = cart.getProducts();
      List<ProductCartDTO> cartDTOs = new ArrayList<>();
      for (Product product : products) {
        ProductCartDTO cartDTO = new ProductCartDTO(product.getId(), product.getAmount());
        cartDTOs.add(cartDTO);
      };
      return cartDTOs;
    } else {
      return null;
    }
  }

  @Override
  public void clearCart(String cartId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart != null) {
      cart.setProducts(new ArrayList<>());
      cartRepository.save(cart);
    }
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
