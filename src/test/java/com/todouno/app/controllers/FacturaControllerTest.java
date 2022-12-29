package com.todouno.app.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.todouno.app.dto.FacturaResponseDTO;
import com.todouno.app.services.FacturaService;

@WebMvcTest(FacturaController.class)
public class FacturaControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private FacturaService facturaService;
	
	@BeforeEach
	void beforeEach(){
		when(facturaService.facturas()).thenReturn(Datos.LISTAFACTURAS);
	}

	@Test
	void testFacturas() throws Exception {
		MockHttpServletResponse response = mockMVC
				.perform(get("/Factura/facturas").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		FacturaResponseDTO result = new ObjectMapper().readValue(response.getContentAsString(),FacturaResponseDTO.class);
		assertTrue(!result.getFacturas().isEmpty());

	}

}
