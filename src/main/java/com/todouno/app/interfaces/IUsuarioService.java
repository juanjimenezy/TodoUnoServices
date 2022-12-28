package com.todouno.app.interfaces;

import com.todouno.app.dto.UsuarioResponseDTO;
import com.todouno.app.models.Usuario;

public interface IUsuarioService {
	
	public UsuarioResponseDTO crearUsuario(Usuario usuario);
	
	public UsuarioResponseDTO logearse(Usuario usuario);

}
