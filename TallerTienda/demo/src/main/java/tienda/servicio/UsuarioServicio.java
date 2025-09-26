package tienda.servicio;

import tienda.modelo.Usuario;
import tienda.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServicio {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }
    
    // Obtener usuario por ID
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }
    
    // Obtener usuario por nombre de usuario
    public Optional<Usuario> obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.findByNombreUsuario(nombreUsuario);
    }
    
    // Obtener usuario por email
    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }
    
    // Obtener usuario por número de documento
    public Optional<Usuario> obtenerUsuarioPorNumeroDocumento(String numeroDocumento) {
        return usuarioRepositorio.findByNumeroDocumento(numeroDocumento);
    }
    
    // Guardar usuario (crear o actualizar)
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
    
    // Crear nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        // Validar que no exista un usuario con el mismo nombre de usuario
        if (usuarioRepositorio.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("Ya existe un usuario con el nombre de usuario: " + usuario.getNombreUsuario());
        }
        
        // Validar que no exista un usuario con el mismo email
        if (usuarioRepositorio.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuario.getEmail());
        }
        
        // Validar que no exista un usuario con el mismo número de documento
        if (usuarioRepositorio.existsByNumeroDocumento(usuario.getNumeroDocumento())) {
            throw new RuntimeException("Ya existe un usuario con el número de documento: " + usuario.getNumeroDocumento());
        }
        
        return usuarioRepositorio.save(usuario);
    }
    
    // Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        // Validar unicidad del nombre de usuario si cambió
        if (!usuarioExistente.getNombreUsuario().equals(usuarioActualizado.getNombreUsuario()) &&
            usuarioRepositorio.existsByNombreUsuario(usuarioActualizado.getNombreUsuario())) {
            throw new RuntimeException("Ya existe un usuario con el nombre de usuario: " + usuarioActualizado.getNombreUsuario());
        }
        
        // Validar unicidad del email si cambió
        if (!usuarioExistente.getEmail().equals(usuarioActualizado.getEmail()) &&
            usuarioRepositorio.existsByEmail(usuarioActualizado.getEmail())) {
            throw new RuntimeException("Ya existe un usuario con el email: " + usuarioActualizado.getEmail());
        }
        
        // Validar unicidad del número de documento si cambió
        if (!usuarioExistente.getNumeroDocumento().equals(usuarioActualizado.getNumeroDocumento()) &&
            usuarioRepositorio.existsByNumeroDocumento(usuarioActualizado.getNumeroDocumento())) {
            throw new RuntimeException("Ya existe un usuario con el número de documento: " + usuarioActualizado.getNumeroDocumento());
        }
        
        // Actualizar campos
        usuarioExistente.setIdTipoDocumento(usuarioActualizado.getIdTipoDocumento());
        usuarioExistente.setNumeroDocumento(usuarioActualizado.getNumeroDocumento());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setNombreUsuario(usuarioActualizado.getNombreUsuario());
        
        return usuarioRepositorio.save(usuarioExistente);
    }
    
    // Eliminar usuario por ID
    public void eliminarUsuario(Long id) {
        if (!usuarioRepositorio.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepositorio.deleteById(id);
    }
    
    // Verificar si existe un usuario con el nombre de usuario dado
    public boolean existeUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.existsByNombreUsuario(nombreUsuario);
    }
    
    // Verificar si existe un usuario con el email dado
    public boolean existeUsuarioPorEmail(String email) {
        return usuarioRepositorio.existsByEmail(email);
    }
    
    // Verificar si existe un usuario con el número de documento dado
    public boolean existeUsuarioPorNumeroDocumento(String numeroDocumento) {
        return usuarioRepositorio.existsByNumeroDocumento(numeroDocumento);
    }
    
    // Obtener usuarios por tipo de documento
    public List<Usuario> obtenerUsuariosPorTipoDocumento(Long idTipoDocumento) {
        return usuarioRepositorio.findByIdTipoDocumento(idTipoDocumento);
    }
    
    // Buscar usuarios por nombre
    public List<Usuario> buscarUsuariosPorNombre(String nombre) {
        return usuarioRepositorio.findByNombreContainingIgnoreCase(nombre);
    }
    
    // Buscar usuarios por nombre de usuario
    public List<Usuario> buscarUsuariosPorNombreUsuario(String nombreUsuario) {
        return usuarioRepositorio.findByNombreUsuarioContainingIgnoreCase(nombreUsuario);
    }
    
    // Autenticar usuario (login)
    public Optional<Usuario> autenticarUsuario(String nombreUsuario, String password) {
        return usuarioRepositorio.findByNombreUsuarioAndPassword(nombreUsuario, password);
    }
    
    // Contar usuarios por tipo de documento
    public long contarUsuariosPorTipoDocumento(Long idTipoDocumento) {
        return usuarioRepositorio.countByIdTipoDocumento(idTipoDocumento);
    }
    
    // Buscar usuarios por dominio de email
    public List<Usuario> buscarUsuariosPorDominioEmail(String dominio) {
        return usuarioRepositorio.findByEmailContainingDomain(dominio);
    }
    
    // Validar datos del usuario antes de guardar
    public void validarUsuario(Usuario usuario) {
        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new RuntimeException("El nombre de usuario es obligatorio");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("El email es obligatorio");
        }
        if (usuario.getNumeroDocumento() == null || usuario.getNumeroDocumento().trim().isEmpty()) {
            throw new RuntimeException("El número de documento es obligatorio");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }
        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }
        if (usuario.getIdTipoDocumento() == null) {
            throw new RuntimeException("El tipo de documento es obligatorio");
        }
    }
} 