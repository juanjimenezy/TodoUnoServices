package com.todouno.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todouno.app.models.Inventario;

@Repository
public interface InventarioRepository extends CrudRepository<Inventario, Long>{
	
	@Query("SELECT i FROM Inventario i WHERE i.producto.id = :idProducto")
	Inventario buscarPorProducto(@Param("idProducto")Long idProducto);

}
