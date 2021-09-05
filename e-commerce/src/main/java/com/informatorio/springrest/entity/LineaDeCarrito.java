package com.informatorio.springrest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class LineaDeCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "linea_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    private Integer cantidad;

    @Transient
    private BigDecimal subtotal;

    public LineaDeCarrito() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }


    public BigDecimal getSubtotal() {
        return producto.getPrecioUnitario().multiply(BigDecimal.valueOf(this.cantidad));
    }


}
