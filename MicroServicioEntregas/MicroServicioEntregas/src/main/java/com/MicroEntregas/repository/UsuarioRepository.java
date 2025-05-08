package com.MicroEntregas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar usuarios por nombre o correo electrónico
    // List<Usuario> findByNombre(String nombre);
    // List<Usuario> findByEmail(String email);
    

       
}
