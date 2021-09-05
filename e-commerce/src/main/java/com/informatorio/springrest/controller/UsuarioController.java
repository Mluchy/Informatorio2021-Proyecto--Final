package com.informatorio.springrest.controller;

import com.informatorio.springrest.entity.Usuario;
import com.informatorio.springrest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // ============ ALTA ============
    @PostMapping
    public ResponseEntity<?> usuarioCreate(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.usSave(usuario),HttpStatus.CREATED);
    }

    // ============ BAJA ============
    @DeleteMapping("/{id}")
    public ResponseEntity<?> usuarioDelete(@PathVariable Long id) {
        usuarioService.usDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ============ MODIFICAR ============
    @PutMapping("/{id}")
    public ResponseEntity<?> usuarioModify(@Valid @PathVariable Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<Usuario>(usuarioService.usModify(id, usuario),HttpStatus.OK);
    }

    // ============ CONSULTAS ============
    @GetMapping({"", "/{id}"})
    public ResponseEntity<?> usuariosConsultas(
            @PathVariable(name = "id", required = false) Long id,
            @RequestParam(name = "ciudad", required = false) String ciudad,
            @RequestParam(name = "fechaAlta", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaAlta) {

        List<Usuario> usuariosList = usuarioService.usFindBy(id, ciudad, fechaAlta);
        return new ResponseEntity<List<Usuario>>(usuariosList,HttpStatus.OK);
    }

}
