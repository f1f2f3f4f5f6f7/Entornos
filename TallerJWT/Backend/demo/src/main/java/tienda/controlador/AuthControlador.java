package tienda.controlador;

import tienda.modelo.Usuario;
import tienda.servicio.UsuarioServicio;
import tienda.seguridad.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

    private final UsuarioServicio usuarioServicio;
    private final JwtService jwtService;

    public AuthControlador(UsuarioServicio usuarioServicio, JwtService jwtService) {
        this.usuarioServicio = usuarioServicio;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return usuarioServicio.autenticarUsuario(req.getNombreUsuario(), req.getPassword())
            .<ResponseEntity<?>>map(user -> {
                String token = jwtService.generateToken(
                    user.getNombreUsuario(),
                    Map.of("nombre", user.getNombre(), "email", user.getEmail())
                );
                return ResponseEntity.ok(new JwtResponse(token, user));
            })
            .orElseGet(() -> ResponseEntity.status(401).body("Credenciales inválidas"));
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario nuevoUsuario) {
        try {
            Usuario creado = usuarioServicio.crearUsuario(nuevoUsuario);
            return ResponseEntity.ok(creado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


}