package com.todouno.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.todouno.app.repositories.InventarioRepository;
import com.todouno.app.services.InventarioService;

@SpringBootTest
class TodoUnoServicesApplicationTests {
	
	@Mock
	private InventarioRepository inventarioRepository;
	
	@InjectMocks
	private InventarioService inventarioService;

	@Test
	void contextLoads() {

		
	}

}
