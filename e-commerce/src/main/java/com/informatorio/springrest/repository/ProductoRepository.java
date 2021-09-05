package com.informatorio.springrest.repository;

import com.informatorio.springrest.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto findOneById(Long id);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByPublicado(String publicado);
}
