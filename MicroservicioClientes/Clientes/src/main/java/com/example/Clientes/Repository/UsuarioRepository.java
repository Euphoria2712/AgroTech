package com.example.Clientes.Repository;

import org.springframework.stereotype.Repository;
import com.example.Clientes.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    Usuarios findByCorreo(String correo);

}
