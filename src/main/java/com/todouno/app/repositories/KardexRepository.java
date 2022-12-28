package com.todouno.app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todouno.app.models.Kardex;

@Repository
public interface KardexRepository extends CrudRepository<Kardex, Long>{
	
	@Query("SELECT k FROM Kardex k WHERE k.producto.id = :idProducto")
	public Kardex buscarPorProducto(@Param("idProducto")Long idProducto);
	
}
