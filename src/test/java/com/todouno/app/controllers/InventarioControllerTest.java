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
import com.todouno.app.models.Inventario;
import com.todouno.app.services.InventarioService;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private InventarioService inventarioService;
	
	@BeforeEach
	void beforeEach() {
		when(this.inventarioService.inventario()).thenReturn(Datos.INVENTARIO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void productos() throws Exception {
		MockHttpServletResponse response = mockMVC
				.perform(get("/Inventario/productos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		List<Inventario> productos = new ObjectMapper().readValue(response.getContentAsString(),ArrayList.class);
		assertTrue(!productos.isEmpty());
		
	}

}
