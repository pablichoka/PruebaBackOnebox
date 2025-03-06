package com.pruebabackonebox.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebabackonebox.dto.CreateProductDTO;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductTests {

	private final MockMvc mockMvc;
	private final ObjectMapper objectMapper;

	@Autowired
	public ProductTests(MockMvc mockMvc, ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@BeforeEach
	public void addInitialProduct() throws Exception {
		CreateProductDTO product = new CreateProductDTO("Producto 1", 3.0);

		mockMvc.perform(post("/product/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Producto 1"))
				.andExpect(jsonPath("$.amount").value(3.0));
	}

	@Test
	public void testGetProduct() throws Exception {
		mockMvc.perform(get("/product/{id}", 0))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Producto 1"))
				.andExpect(jsonPath("$.amount").value(3.0));
	}

	@Test
	public void testAddProduct() throws Exception {
		CreateProductDTO product = new CreateProductDTO("Producto 2", 2.0);

		mockMvc.perform(post("/product/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Producto 2"))
				.andExpect(jsonPath("$.amount").value(2.0));
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
				.param("id", "0"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateProduct() throws Exception {
		CreateProductDTO product = new CreateProductDTO("Producto 1 Modificado", 4.0);

		mockMvc.perform(put("/product/update/{id}", 0)				
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.description").value("Producto 1 Modificado"))
				.andExpect(jsonPath("$.amount").value(4.0));
	}

}
