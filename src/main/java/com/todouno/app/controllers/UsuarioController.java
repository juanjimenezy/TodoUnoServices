package com.todouno.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todouno.app.dto.UsuarioResponseDTO;
import com.todouno.app.interfaces.IUsuarioService;
import com.todouno.app.models.Usuario;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping("/crearUsuario")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
		Map<String,Object> response = new HashMap<>();
		UsuarioResponseDTO newUsuario = null;
		try {
			newUsuario = usuarioService.crearUsuario(usuario);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<UsuarioResponseDTO>(newUsuario, HttpStatus.OK);
	}
	
	@PostMapping("/logearse")
	public ResponseEntity<?> logearse(@RequestBody Usuario usuario){
		Map<String,Object> response = new HashMap<>();
		UsuarioResponseDTO lUsuario = null;
		try {
			lUsuario = usuarioService.logearse(usuario);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UsuarioResponseDTO>(lUsuario,HttpStatus.OK);
	}

}
