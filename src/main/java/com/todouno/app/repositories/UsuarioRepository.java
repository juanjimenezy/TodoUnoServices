package com.todouno.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todouno.app.models.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long>{
	
	@Query("SELECT u FROM Usuario u WHERE u.username = :username")
	public Usuario login(@Param("username")String username);

}
