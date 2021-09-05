package com.informatorio.springrest.service;

import com.informatorio.springrest.entity.Producto;
import com.informatorio.springrest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    //Para consultas Get
    public List<Producto> prFindBy(Long id, String nombre, String publicado) {

        List<Producto> productoList = new ArrayList<>();

        if (Objects.nonNull(id)) {
            productoList.add(productoRepository.findOneById(id));
            return productoList;
        } else if (Objects.nonNull(nombre)) {
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        } else if (Objects.nonNull(publicado)) {
            return productoRepository.findByPublicado(publicado);
        }
        return productoRepository.findAll();
    }


    //Para Put
    public Producto prModify(Long id, Producto producto){
        Producto productoExistente = productoRepository.findOneById(id);
        if (Objects.nonNull(producto.getDescripcion())){
            productoExistente.setDescripcion(producto.getDescripcion());
        }
        if (Objects.nonNull(producto.getPrecioUnitario())) {
            productoExistente.setPrecioUnitario(producto.getPrecioUnitario());
        }
        if (Objects.nonNull(producto.getContenido())){
            productoExistente.setContenido(producto.getContenido());
        }
        if (Objects.nonNull(producto.getPublicado())){
            productoExistente.setPublicado(producto.getPublicado());
        }
        return productoRepository.save(productoExistente);
    }

    //Para Delete
    public void prDelete(Long id) {
        productoRepository.deleteById(id);
    }

    //Para Post
    public List<Producto> prCreate(List<Producto> productos) {
        return productoRepository.saveAll(productos);
    }

}
