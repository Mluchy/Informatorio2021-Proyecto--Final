package com.informatorio.springrest.repository;

import com.informatorio.springrest.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findOneById(Long id);

    List<Usuario> findByFechaAltaAfter(LocalDateTime fechaAlta);

    List<Usuario> findByCiudadIgnoreCase(String ciudad);
}
