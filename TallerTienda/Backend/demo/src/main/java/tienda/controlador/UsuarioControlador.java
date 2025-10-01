package tienda.controlador;

import tienda.modelo.Usuario;
import tienda.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios() {
        try {
            List<Usuario> usuarios = usuarioServicio.obtenerTodosUsuarios();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorId(id);
            return usuario.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener usuario por nombre de usuario
    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombreUsuario(@PathVariable String nombreUsuario) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorNombreUsuario(nombreUsuario);
            return usuario.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener usuario por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorEmail(email);
            return usuario.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener usuario por número de documento
    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<Usuario> obtenerUsuarioPorNumeroDocumento(@PathVariable String numeroDocumento) {
        try {
            Optional<Usuario> usuario = usuarioServicio.obtenerUsuarioPorNumeroDocumento(numeroDocumento);
            return usuario.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Crear nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            usuarioServicio.validarUsuario(usuario);
            Usuario usuarioCreado = usuarioServicio.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
        try {
            Usuario usuarioActualizado = usuarioServicio.actualizarUsuario(id, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Verificar existencia de usuario por nombre de usuario
    @GetMapping("/existe/usuario/{nombreUsuario}")
    public ResponseEntity<Boolean> existeUsuarioPorNombreUsuario(@PathVariable String nombreUsuario) {
        try {
            boolean existe = usuarioServicio.existeUsuarioPorNombreUsuario(nombreUsuario);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Verificar existencia de usuario por email
    @GetMapping("/existe/email/{email}")
    public ResponseEntity<Boolean> existeUsuarioPorEmail(@PathVariable String email) {
        try {
            boolean existe = usuarioServicio.existeUsuarioPorEmail(email);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Verificar existencia de usuario por número de documento
    @GetMapping("/existe/documento/{numeroDocumento}")
    public ResponseEntity<Boolean> existeUsuarioPorNumeroDocumento(@PathVariable String numeroDocumento) {
        try {
            boolean existe = usuarioServicio.existeUsuarioPorNumeroDocumento(numeroDocumento);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener usuarios por tipo de documento
    @GetMapping("/tipo-documento/{idTipoDocumento}")
    public ResponseEntity<List<Usuario>> obtenerUsuariosPorTipoDocumento(@PathVariable Integer idTipoDocumento) {
        try {
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorTipoDocumento(idTipoDocumento);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Buscar usuarios por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorNombre(@RequestParam String nombre) {
        try {
            List<Usuario> usuarios = usuarioServicio.buscarUsuariosPorNombre(nombre);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Buscar usuarios por nombre de usuario
    @GetMapping("/buscar/usuario")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorNombreUsuario(@RequestParam String nombreUsuario) {
        try {
            List<Usuario> usuarios = usuarioServicio.buscarUsuariosPorNombreUsuario(nombreUsuario);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Autenticar usuario (login)
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<Usuario> usuario = usuarioServicio.autenticarUsuario(
                loginRequest.getNombreUsuario(), 
                loginRequest.getPassword()
            );
            
            if (usuario.isPresent()) {
                return ResponseEntity.ok(usuario.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                   .body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Contar usuarios por tipo de documento
    @GetMapping("/contar/tipo-documento/{idTipoDocumento}")
    public ResponseEntity<Long> contarUsuariosPorTipoDocumento(@PathVariable Integer idTipoDocumento) {
        try {
            long cantidad = usuarioServicio.contarUsuariosPorTipoDocumento(idTipoDocumento);
            return ResponseEntity.ok(cantidad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Buscar usuarios por dominio de email
    @GetMapping("/buscar/email")
    public ResponseEntity<List<Usuario>> buscarUsuariosPorDominioEmail(@RequestParam String dominio) {
        try {
            List<Usuario> usuarios = usuarioServicio.buscarUsuariosPorDominioEmail(dominio);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Clase interna para el request de login
    public static class LoginRequest {
        private String nombreUsuario;
        private String password;
        
        public String getNombreUsuario() {
            return nombreUsuario;
        }
        
        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }
        
        public String getPassword() {
            return password;
        }
        
        public void setPassword(String password) {
            this.password = password;
        }
    }
} 