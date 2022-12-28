package com.todouno.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todouno.app.models.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
