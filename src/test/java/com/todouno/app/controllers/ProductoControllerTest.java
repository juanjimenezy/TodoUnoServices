package com.todouno.app.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todouno.app.Datos;
import com.todouno.app.models.Producto;
import com.todouno.app.services.ProductoService;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private ProductoService productoService;
	
	@BeforeEach
	void beforeEach() {
		when(productoService.buscarTodos()).thenReturn(Datos.LISTAPRODUCTOS);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testProductos() throws Exception{
		MockHttpServletResponse response = mockMVC
				.perform(get("/Producto/productos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		List<Producto> productos = new ObjectMapper().readValue(response.getContentAsString(),ArrayList.class);
		assertTrue(!productos.isEmpty());
	}

}
