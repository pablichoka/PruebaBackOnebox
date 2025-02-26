package com.pruebabackonebox.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private final ConcurrentHashMap<String, ScheduledFuture<?>> cartTimers = new ConcurrentHashMap<>();

  @Autowired
  public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
    this.cartRepository = cartRepository;
    this.productService = productService;
  }

  @Override
  @Transactional
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
    cart.updateTimestamp();
    cartRepository.save(cart);
    resetTimer(cartId);

    return new ProductCartDTO(productId, cartProduct.getQuantity(), product.getAmount());
  }

  @Override
  @Transactional
  public boolean removeProductFromCart(String cartId, Integer productId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart != null) {
      Set<CartProduct> products = cart.getCartProducts();
      CartProductId cartProductId = new CartProductId(cart.getId(), productId);
      products.removeIf(product -> product.getId().equals(cartProductId));
      cart.setCartProducts(products);
      cart.updateTimestamp();
      cartRepository.save(cart);
      resetTimer(cartId);
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
  @Transactional
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
  @Transactional
  public void deleteCart(String cartId) {
    cartRepository.deleteById(cartId);
  }

  @Override
  @Transactional
  public String createCart() {
    Cart cart = new Cart();
    cartRepository.save(cart);
    startTimer(cart.getId());
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

  @Override
  @Transactional
  public void startTimer(String cartId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found");
    }
    try {
      Runnable cartExpiryChecker = new Runnable() {
        @Override
        public void run() {
          cartRepository.deleteById(cartId);
          cartTimers.remove(cartId);
        }
      };
      ScheduledFuture<?> scheduledFuture = scheduler.schedule(cartExpiryChecker, 10, TimeUnit.MINUTES);
      cartTimers.put(cartId, scheduledFuture);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void resetTimer(String cartId) {
    try {
      ScheduledFuture<?> scheduledFuture = cartTimers.get(cartId);
      if (scheduledFuture != null) {
        scheduledFuture.cancel(true);
      }
      startTimer(cartId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
