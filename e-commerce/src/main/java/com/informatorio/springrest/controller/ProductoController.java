package com.informatorio.springrest.controller;

import com.informatorio.springrest.entity.Producto;
import com.informatorio.springrest.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {


    private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ============ ALTA ============
    @PostMapping
    public ResponseEntity<?> productoCreate(@Valid @RequestBody List<Producto> producto) {
        return new ResponseEntity<>(productoService.prCreate(producto),HttpStatus.CREATED);
    }

    // ============ BAJA ============
    @DeleteMapping("/{id}")
    public ResponseEntity<?> productoDelete(@PathVariable Long id) {
        productoService.prDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ============ MODIFICACIONES ============
    @PutMapping("/{id}")
    public ResponseEntity<?> productoModify(@Valid @PathVariable Long id, @RequestBody Producto producto) {
        return new ResponseEntity<Producto>(productoService.prModify(id, producto),HttpStatus.OK);
    }

    // ============ CONSULTAS ============
    @GetMapping({"", "/{id}"})
    public ResponseEntity<?> productoConsultas(
            @PathVariable(name = "id", required = false) Long id,
            @Valid @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "publicado", required = false) String publicado) {

        List<Producto> productoList = productoService.prFindBy(id, nombre, publicado);
        return new ResponseEntity<List<Producto>>(productoList,HttpStatus.OK);
    }



}
