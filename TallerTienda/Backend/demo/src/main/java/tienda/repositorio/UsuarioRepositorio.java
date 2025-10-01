package tienda.repositorio;

import tienda.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    
    // Buscar usuario por nombre de usuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);
    
    // Buscar usuario por número de documento
    Optional<Usuario> findByNumeroDocumento(String numeroDocumento);
    
    // Verificar si existe un usuario con el nombre de usuario dado
    boolean existsByNombreUsuario(String nombreUsuario);
    
    // Verificar si existe un usuario con el email dado
    boolean existsByEmail(String email);
    
    // Verificar si existe un usuario con el número de documento dado
    boolean existsByNumeroDocumento(String numeroDocumento);
    
    // Buscar usuarios por tipo de documento
    List<Usuario> findByIdTipoDocumento(Integer idTipoDocumento);
    
    // Buscar usuarios por nombre (búsqueda parcial)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar usuarios por nombre de usuario (búsqueda parcial)
    List<Usuario> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);
    
    // Consulta personalizada para buscar usuarios activos (si agregamos campo activo en el futuro)
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.password = :password")
    Optional<Usuario> findByNombreUsuarioAndPassword(@Param("nombreUsuario") String nombreUsuario, 
                                                    @Param("password") String password);
    
    // Consulta personalizada para contar usuarios por tipo de documento
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.idTipoDocumento = :idTipoDocumento")
    long countByIdTipoDocumento(@Param("idTipoDocumento") Integer idTipoDocumento);
    
    // Consulta personalizada para buscar usuarios con email que contenga un dominio específico
    @Query("SELECT u FROM Usuario u WHERE u.email LIKE %:dominio%")
    List<Usuario> findByEmailContainingDomain(@Param("dominio") String dominio);
} 

