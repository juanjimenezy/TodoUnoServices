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
		usuario.setUsername(this.encryptService.encrypt(usuario.getUsername()));
		usuario.setPassword(this.encryptService.encrypt(usuario.getPassword()));
		usuario.setNumTarjeta(this.encryptService.encrypt(usuario.getNumTarjeta()));
		usuario = this.usuarioRepository.save(usuario);
		return convertObjectUsuario(usuario);
	}

	@Override
	public UsuarioResponseDTO logearse(Usuario usuario) {
		Usuario lUsuario = this.usuarioRepository.login(this.encryptService.encrypt(usuario.getUsername()));
		String pass = this.encryptService.encrypt(usuario.getPassword());
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
		dto.setUsername(this.encryptService.decrypt(usuario.getUsername()));
		return dto;
	}
}
