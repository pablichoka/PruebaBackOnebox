package com.pruebabackonebox.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebabackonebox.dto.AddProductDTO;
import com.pruebabackonebox.dto.DeleteProductDTO;

@SpringBootTest
@AutoConfigureMockMvc
class CartTests {

  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;
  private final ProductTests productTests;

  @Autowired
  public CartTests(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
    this.productTests = new ProductTests(mockMvc, objectMapper);
  }

  @BeforeEach
  public void addInitialProduct() {
    try {
      productTests.testAddProduct();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  

  @Test
  public void testCreateCart() throws Exception {
    mockMvc.perform(get("/cart/add"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isString());
  }

  @Test
  public void testGetAllCarts() throws Exception {
    mockMvc.perform(get("/cart/all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testGetCart() throws Exception {
    String cartId = createCart();

    mockMvc.perform(get("/cart/{id}", cartId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray());
  }

  @Test
  public void testAddProductToCart() throws Exception {
    String cartId = createCart();
    AddProductDTO productDTO = new AddProductDTO(1, 2);

    mockMvc.perform(post("/cart/add/{id}", cartId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(1))
        .andExpect(jsonPath("$.quantity").value(2));
  }

  @Test
  public void testDeleteProductFromCart() throws Exception {
    String cartId = createCart();
    AddProductDTO addProductDTO = new AddProductDTO(1, 2);

    mockMvc.perform(post("/cart/add/{id}", cartId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(addProductDTO)))
        .andExpect(status().isOk());

    DeleteProductDTO deleteProductDTO = new DeleteProductDTO(1);

    mockMvc.perform(post("/cart/delete-product/{id}", cartId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(deleteProductDTO)))
        .andExpect(status().isOk());
  }

  @Test
  public void testClearCart() throws Exception {
    String cartId = createCart();

    mockMvc.perform(delete("/cart/clear/{id}", cartId))
        .andExpect(status().isOk());
  }

  @Test
  public void testDeleteCart() throws Exception {
    String cartId = createCart();

    mockMvc.perform(delete("/cart/delete/{id}", cartId))
        .andExpect(status().isOk());
  }

  @Test
  public void testGetTotalPrice() throws Exception {
    String cartId = createCart();
    AddProductDTO productDTO = new AddProductDTO(1, 2);

    mockMvc.perform(post("/cart/add/{id}", cartId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productDTO)))
        .andExpect(status().isOk());

    mockMvc.perform(get("/cart/total/{id}", cartId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNumber());
  }

  private String createCart() throws Exception {
    return mockMvc.perform(get("/cart/add"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();
  }
}