package com.todouno.app.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.todouno.app.Datos;
import com.todouno.app.models.Inventario;
import com.todouno.app.services.InventarioService;

@SpringBootTest
public class InventarioRepositoryTest {
	
	@Mock
	private InventarioRepository inventarioRepository;
	
	@InjectMocks
	private InventarioService inventarioService;
	
	@BeforeEach
	void beforeEach() {
		when(inventarioRepository.findAll()).thenReturn(Datos.INVENTARIO);
		when(inventarioRepository.buscarPorProducto(anyLong())).thenReturn(Datos.INVENTARIO1);
	}
	
	@Test
	void testBuscarTodos() {
		List<Inventario> lInventario = inventarioService.inventario();
		assertTrue(!lInventario.isEmpty());
	}
	
	@Test
	void testCantidad() {
		Integer cantidad = inventarioService.cantidad(Datos.PRODUCTO1);
		assertTrue(cantidad >= 0);
		assertNotNull(cantidad);
	}

}
