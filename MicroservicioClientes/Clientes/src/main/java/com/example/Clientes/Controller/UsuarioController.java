package com.example.Clientes.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Clientes.Model.LoginRequisito;
import com.example.Clientes.Model.Usuarios;
import com.example.Clientes.Service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("api/v1/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuarios guardarUsuario(@RequestBody Usuarios usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuarios buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @GetMapping
    public List<Usuarios> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PutMapping("/{id}")
    public Usuarios modificarUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        return usuarioService.modificarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuarioPorId(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequisito LoginRequisito){


        Usuarios usuario = usuarioService.buscarPorCorreo(LoginRequisito.getCorreo());

        if(usuario != null && usuario.getContraseña().equals(LoginRequisito.getContraseña())){
           return ResponseEntity.ok("Login exitoso, Bienvendio "+usuario.getNombre());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    
    }

    
    @PostMapping("/admin")
    public ResponseEntity<?> acessoAdmin(@RequestBody LoginRequisito LoginRequisito){
       Usuarios usuario = usuarioService.buscarPorCorreo(LoginRequisito.getCorreo());

       if (usuario != null && usuario.getContraseña().equals(LoginRequisito.getContraseña())) {
            if ("ADMIN".equalsIgnoreCase(usuario.getRol())) {
                return ResponseEntity.ok("Acceso concedido. bienvenido Administrador: " + usuario.getNombre());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado. Solo para administradores.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado. Credenciales incorrectas.");
        }

       }
    
}
    