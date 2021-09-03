package com.informatorio.springrest.controller;

import com.informatorio.springrest.entity.Usuario;
import com.informatorio.springrest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
   
    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /* TODO
     * 1- ALTA, BAJA Y MODIFICACIÓN
     * 2- CONSULTA (OBTENER TODOS LOS USUARIOS) -> ESCONDER PAS
     * 3- CONSULTA (OBTENER TODOS LOS USUARIOS DE LA CIUDAD DE RESISTENCIA)
     * 4- CONSULTA (OBTENER TODOS LOS USUARIOS QUE FUERON CREADOS DESPUÉS DE UNA FECHA)
     *
     * Borrar carga Bulk de Usuario > es sólo una carga rápida para probar
     *
     * Agregar los errorHandler
     *
     * */

    // ============ ALTA ============

    //POST bulk de usuarios > DEBO BORRAR
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Usuario> usuarioCreate(@Valid @RequestBody List<Usuario> usuario) {
        return usuarioRepository.saveAll(usuario);
    }


    //POST un usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario usuarioCreate(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ============ BAJA ============
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void personaDelete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    // ============ MODIFICAR ============
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario usuarioModify(@Valid @PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findOneById(id);
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setCiudad(usuario.getCiudad());
        usuarioExistente.setProvincia(usuario.getProvincia());
        usuarioExistente.setPais(usuario.getPais());
        return usuarioRepository.save(usuarioExistente);
    }



    // ============ CONSULTAS ============
    //GET juntados
    @GetMapping({"", "/{id}"})
    public ResponseEntity<?> usuariosConsultas(
            @PathVariable(name = "id", required = false) Long id,
            @RequestParam(name = "ciudad", required = false) String ciudad,
            @RequestParam(name = "fechaAlta", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaAlta) {
        if (Objects.nonNull(id)) {
            return new ResponseEntity<>(usuarioRepository.findOneById(id), HttpStatus.OK);
        } else if (Objects.nonNull(ciudad)) {
            return new ResponseEntity<>(usuarioRepository.findByCiudadIgnoreCase(ciudad),HttpStatus.OK);
        } else if (Objects.nonNull(fechaAlta)) {
            return new ResponseEntity<>(usuarioRepository.findByFechaAltaAfter(fechaAlta.atStartOfDay()),HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
    }




}
