package com.informatorio.springrest.service;

import com.informatorio.springrest.entity.Usuario;
import com.informatorio.springrest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Para el Get > todas las consultas
    public List<Usuario> usFindBy(Long id, String ciudad, LocalDate fechaAlta) {

        List<Usuario> usuarioList = new ArrayList<Usuario>();

        if (Objects.nonNull(id)) {
            usuarioList.add(usuarioRepository.findOneById(id));
            return usuarioList;
        } else if (Objects.nonNull(ciudad)) {
            return usuarioRepository.findByCiudadIgnoreCase(ciudad);
        } else if (Objects.nonNull(fechaAlta)) {
            return usuarioRepository.findByFechaAltaAfter(fechaAlta.atStartOfDay());
        }
        return usuarioRepository.findAll();
    }

    //Para el Put
    public Usuario usModify(Long id, Usuario usuario){
        Usuario usuarioExistente = usuarioRepository.findOneById(id);
        if (Objects.nonNull(usuario.getPassword())){
            usuarioExistente.setPassword(usuario.getPassword());
        }
        if (Objects.nonNull(usuario.getCiudad())) {
            usuarioExistente.setCiudad(usuario.getCiudad());
        }
        if (Objects.nonNull(usuario.getProvincia())){
            usuarioExistente.setProvincia(usuario.getProvincia());
        }
        if (Objects.nonNull(usuario.getPais())) {
            usuarioExistente.setPais(usuario.getPais());
        }
        return usuarioRepository.save(usuarioExistente);
    }

    //Para el Post
    public Usuario usSave(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Para el Delete
    public void usDelete(Long id) {
        usuarioRepository.deleteById(id);
    }


}
