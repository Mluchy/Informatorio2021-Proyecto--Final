package com.informatorio.springrest.repository;

import com.informatorio.springrest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /* TODO
    * Agregar el IgnoreCase
    * */


    public Usuario findOneById(Long id);

    List<Usuario> findByFechaAltaAfter(LocalDateTime fechaAlta);

    List<Usuario> findByCiudadIgnoreCase(String ciudad);
}
