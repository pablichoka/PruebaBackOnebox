package com.pruebabackonebox.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebabackonebox.dto.ProductDTO;
import com.pruebabackonebox.model.Product;
import com.pruebabackonebox.repository.ProductRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TestApplicationTests {

	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;
	private final ProductRepository productRepository;

	@Autowired
	public TestApplicationTests(MockMvc mockMvc, ObjectMapper objectMapper, ProductRepository productRepository) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
		this.productRepository = productRepository;
	}

	@BeforeEach
    public void setup() {
        Product product = new Product();
        product.setId(1);
        product.setDescription("Producto 1");
        product.setAmount(100.0);
        productRepository.save(product);
    }

	@Test
	public void testAddProduct() throws Exception {
		ProductDTO product = new ProductDTO("Producto 2", 2.0);

		mockMvc.perform(post("/product/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Producto 2"))
				.andExpect(jsonPath("$.amount").value(2.0));
	}

	@Test
	public void testGetProduct() throws Exception {
		mockMvc.perform(get("/product/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(get("/product/all"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	public void testDeleteProduct() throws Exception {
		mockMvc.perform(delete("/product/delete")
				.param("id", "1"))
				.andExpect(status().isOk());
	}

}
