package com.MicroEntregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MicroEntregas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByRol(String rol);
    List<Usuario> findByNombreAndEmail(String nombre, String email);
    
}
