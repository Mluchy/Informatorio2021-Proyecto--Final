package com.informatorio.springrest.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.informatorio.springrest.util.ValidationHelper;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank(message = "El nombre no debe ser vacío")
    private String nombre;

    @NotBlank(message = "El apellido no debe ser vacío")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Column(unique = true)
    @Email(regexp = ValidationHelper.EMAIL_REGEX)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "La contraseña no debe estar vacía")
    @Size(min = 8, message = "La contraseña debe ser de 8 o más caracteres")
    private String password;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    private LocalDateTime fechaAlta;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String provincia;

    @NotBlank
    private String pais;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos = new ArrayList<>();

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public void agregarCarrito(Carrito carrito) {
        carritos.add(carrito);
        carrito.setUsuario(this);
    }

    public void removerCarrito(Carrito carrito) {
        carritos.remove(carrito);
        carrito.setUsuario(null);
    }

    /*
    *  //Getters
    public Long getId() {return id;}
    public Boolean getEstado() {return estado;}
    public Long getUsuarioId() {return usuario.getId();}
    @JsonIgnore
    public Usuario getUsuario() {return usuario;}
    public Date getFecha_creacion() {return fecha_creacion;}
    public List<Detalle> getDetalle() {return detalle;}
    public Origen getOrigen() {return origen;}
    @Transient
    public Double getCostoTotal(){
        Double total = 0.0;
        for ( Detalle d : this.getDetalle()){
            total = d.getSubTotal() + total;
        }
        return total;
    }

    //Setters
    public void addDetalle(Detalle detalle){this.getDetalle().add(detalle);}
    public void removeDetalle(Detalle detalle){
        this.getDetalle().remove(detalle);
    }
    public void setEstado(Boolean estado) {this.estado = estado;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}
    public void setOrigen(Origen origen) { this.origen = origen;}
    * */

}
