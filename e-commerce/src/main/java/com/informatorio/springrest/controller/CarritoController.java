package com.informatorio.springrest.controller;

import com.informatorio.springrest.dto.CarritoOperaciones;
import com.informatorio.springrest.entity.Carrito;
import com.informatorio.springrest.entity.LineaDeCarrito;
import com.informatorio.springrest.entity.Producto;
import com.informatorio.springrest.entity.Usuario;
import com.informatorio.springrest.repository.CarritoRepository;
import com.informatorio.springrest.repository.ProductoRepository;
import com.informatorio.springrest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarritoController {

    private CarritoRepository carritoRepository;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public CarritoController(CarritoRepository carritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }




/* TODO
* Alta, baja y modificación
* En el Alta, debo controlar que no haya otro abierto
* Baja, es quitar los elementos
* Modificar, los elementos que tiene
*
*
* */


    // ============ ALTA ============
    @PostMapping("/usuarios/{id}/carritos")
    public ResponseEntity<?> carritoCreate(@PathVariable("id") Long id,
                                          @Valid @RequestBody Carrito carrito) {
        Usuario usuario = usuarioRepository.findOneById(id);

        List<Carrito> carritoList = usuario.getCarritos();
        if (!carritoList.isEmpty()) {
            Carrito carritoUltimo = carritoList.get(carritoList.size()-1);
            if (carritoUltimo.isEstado()) {
                return new ResponseEntity<String>("Ya posee un carrito activo", HttpStatus.OK);
            } else {
                usuario.agregarCarrito(carrito);
                return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);
            }
        }

        usuario.agregarCarrito(carrito);
        return new ResponseEntity<>(carritoRepository.save(carrito), HttpStatus.CREATED);

    }
    // ============ BAJA ============



    // ============ MODIFICAR ============
    @PutMapping("/usuarios/{id}/carritos/{carritoId}")
    public ResponseEntity<?> agregarProducto(@PathVariable Long id,
                                             @PathVariable Long carritoId,
                                             @Valid @RequestBody CarritoOperaciones carritoOperaciones) {

        Carrito carrito = carritoRepository.getById(carritoId);
        Producto producto = productoRepository.getById(carritoOperaciones.getProductoId());
        LineaDeCarrito lineaDeCarrito = new LineaDeCarrito();
        lineaDeCarrito.setCarrito(carrito);
        lineaDeCarrito.setProducto(producto);
        lineaDeCarrito.setCantidad(carritoOperaciones.getCantidad());
        carrito.agregarLineDeCarrito(lineaDeCarrito);
        return new ResponseEntity<>(carritoRepository.save(carrito),HttpStatus.OK);

    }


    // ============ CONSULTAS ============

    @GetMapping("/usuarios/{id}/carritos")
    public ResponseEntity<?> carritosDelUsuarioAll(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findOneById(id);
        List<Carrito> carritoList = usuario.getCarritos();
        return new ResponseEntity<>(carritoList,HttpStatus.OK);
    }



    @GetMapping("/usuarios/{id}/estado")
    public ResponseEntity<?> carritosActivos(@PathVariable Long id){
        Usuario usuario = usuarioRepository.findOneById(id);
        List<Carrito> carritoList = usuario.getCarritos();

        if (!carritoList.isEmpty()) {
            Carrito carritoUltimo = carritoList.get(carritoList.size()-1);
            if (carritoUltimo.isEstado()) {
                return new ResponseEntity<>(carritoUltimo,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("No posee carritos activos",HttpStatus.OK);
    }


}
