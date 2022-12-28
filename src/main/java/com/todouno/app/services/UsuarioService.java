package com.todouno.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todouno.app.dto.UsuarioResponseDTO;
import com.todouno.app.interfaces.IUsuarioService;
import com.todouno.app.models.Usuario;
import com.todouno.app.repositories.UsuarioRepository;
import com.todouno.app.security.IEncryptService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private IEncryptService encryptService;

	@Override
	public UsuarioResponseDTO crearUsuario(Usuario usuario) {
		usuario.setUsername(encryptService.encrypt(usuario.getUsername()));
		usuario.setPassword(encryptService.encrypt(usuario.getPassword()));
		usuario.setNumTarjeta(encryptService.encrypt(usuario.getNumTarjeta()));
		usuario = usuarioRepository.save(usuario);
		return convertObjectUsuario(usuario);
	}

	@Override
	public UsuarioResponseDTO logearse(Usuario usuario) {
		Usuario lUsuario = usuarioRepository.login(encryptService.encrypt(usuario.getUsername()));
		String pass = encryptService.encrypt(usuario.getPassword());
		if(lUsuario.getPassword().equals(pass)){
			return convertObjectUsuario(lUsuario);
		}
		return null;
	}
	
	public UsuarioResponseDTO convertObjectUsuario(Usuario usuario) {
		UsuarioResponseDTO dto = new UsuarioResponseDTO();
		dto.setId(usuario.getId());
		dto.setNit(usuario.getNit());
		dto.setNombre(usuario.getNombre());
		dto.setApellido(usuario.getApellido());
		dto.setUsername(encryptService.decrypt(usuario.getUsername()));
		return dto;
	}
}
