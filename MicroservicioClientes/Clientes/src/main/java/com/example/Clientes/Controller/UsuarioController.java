package com.example.Clientes.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Clientes.Model.LoginRequisito;
import com.example.Clientes.Model.Usuarios;
import com.example.Clientes.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/api/v1/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuarios usuario){
        try {
            Usuarios nuevo = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.ok("Usuario guardado correctamente: " + nuevo.getNombre());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el usuario: " + e.getMessage());
        }    

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id){
        Usuarios usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado con ID: " + id);
        }
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosLosUsuarios(){
        try {
            return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los usuarios: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuarios usuario){
        try {
            Usuarios usuarioModificado = usuarioService.modificarUsuario(id, usuario);
            return ResponseEntity.ok("Usuario modificado correctamente: " + usuarioModificado.getNombre());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al modificar el usuario: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable Long id){
        try {
            usuarioService.eliminarUsuarioPorId(id);
            return ResponseEntity.ok("Usuario eliminado correctamente con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el usuario: " + e.getMessage());
        }
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
    