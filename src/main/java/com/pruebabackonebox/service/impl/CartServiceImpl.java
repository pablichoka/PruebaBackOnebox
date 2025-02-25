package com.pruebabackonebox.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebabackonebox.dto.ProductCartDTO;
import com.pruebabackonebox.model.Cart;
import com.pruebabackonebox.model.CartProduct;
import com.pruebabackonebox.model.CartProductId;
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
  public ProductCartDTO addProductToCart(String cartId, Integer productId, Integer quantity) {
    if (!productService.productExists(productId) || !cartRepository.existsById(cartId)) {
      return null;
    }

    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than 0");
    }

    Product product = productService.getFullProduct(productId);
    Cart cart = cartRepository.findById(cartId).orElse(null);

    CartProductId cartProductId = new CartProductId(cart.getId(), product.getId());
    Set<CartProduct> products = cart.getCartProducts();
    CartProduct cartProduct = products.stream()
        .filter(cp -> cp.getId().equals(cartProductId))
        .findFirst()
        .orElse(new CartProduct(cartProductId, cart, product, 1));

    cartProduct.setQuantity(quantity);
    products.add(cartProduct);
    cart.setCartProducts(products);
    cartRepository.save(cart);

    return new ProductCartDTO(productId, cartProduct.getQuantity(), product.getAmount());
  }

  @Override
  public boolean removeProductFromCart(String cartId, Integer productId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart != null) {
      Set<CartProduct> products = cart.getCartProducts();
      CartProductId cartProductId = new CartProductId(cart.getId(), productId);
      products.removeIf(product -> product.getId().equals(cartProductId));
      cart.setCartProducts(products);
      cartRepository.save(cart);
      return true;
    } else {
      return false;
    }

  }

  @Override
  public Set<ProductCartDTO> getCartDetails(String cartId) {
    boolean cartExists = cartRepository.existsById(cartId);
    if (cartExists != false) {
      Cart cart = cartRepository.findById(cartId).get();
      Set<CartProduct> products = cart.getCartProducts();
      Set<ProductCartDTO> cartDTOs = new HashSet<>();
      for (CartProduct cartProduct : products) {
        Product product = productService.getFullProduct(cartProduct.getProduct().getId());
        ProductCartDTO cartDTO = new ProductCartDTO(product.getId(), cartProduct.getQuantity(), product.getAmount());
        cartDTOs.add(cartDTO);
      }
      ;
      return cartDTOs;
    } else {
      return null;
    }
  }

  @Override
  public void clearCart(String cartId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart != null) {
      cart.getCartProducts().clear();
      cartRepository.save(cart);
    }
  }

  @Override
  public String[] getAllCarts() {
    Set<Cart> carts = cartRepository.findAll();
    String[] cartIds = new String[carts.size()];
    int i = 0;
    for (Cart cart : carts) {
      cartIds[i++] = cart.getId();
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

  @Override
  public Double getTotalPrice(String id) {
    Cart cart = cartRepository.findById(id).orElse(null);
    if (cart == null)
      return null;
    Set<CartProduct> products = cart.getCartProducts();
    Double totalAmount = 0.0;
    for (CartProduct cartProduct : products) {
      Product product = productService.getFullProduct(cartProduct.getProduct().getId());
      totalAmount += cartProduct.getQuantity() * product.getAmount();
    }
    return totalAmount;
  }

}
