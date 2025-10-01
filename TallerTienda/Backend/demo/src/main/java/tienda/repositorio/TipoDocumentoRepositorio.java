package tienda.repositorio;

import tienda.modelo.Tipodocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDocumentoRepositorio extends JpaRepository<Tipodocumento, Integer> {
    
    // Buscar tipo de documento por tipo exacto
    Optional<Tipodocumento> findByTipo(String tipo);
    
    // Buscar tipo de documento por tipo (insensible a mayúsculas/minúsculas)
    Optional<Tipodocumento> findByTipoIgnoreCase(String tipo);
    
    // Verificar si existe un tipo de documento con el tipo dado
    boolean existsByTipo(String tipo);
    
    // Verificar si existe un tipo de documento con el tipo dado (insensible a mayúsculas/minúsculas)
    boolean existsByTipoIgnoreCase(String tipo);
    
    // Buscar tipos de documento que contengan un texto específico
    List<Tipodocumento> findByTipoContainingIgnoreCase(String tipo);
    
    // Consulta personalizada para obtener todos los tipos de documento ordenados por tipo
    @Query("SELECT t FROM Tipodocumento t ORDER BY t.tipo ASC")
    List<Tipodocumento> findAllOrderByTipo();
    
    // Consulta personalizada para contar cuántos usuarios tienen cada tipo de documento
    @Query("SELECT t.tipo, COUNT(u.id) FROM Tipodocumento t LEFT JOIN Usuario u ON t.id = u.idTipoDocumento GROUP BY t.id, t.tipo ORDER BY COUNT(u.id) DESC")
    List<Object[]> countUsuariosByTipoDocumento();
    
    // Consulta personalizada para obtener tipos de documento que no tienen usuarios asociados
    @Query("SELECT t FROM Tipodocumento t WHERE t.id NOT IN (SELECT DISTINCT u.idTipoDocumento FROM Usuario u WHERE u.idTipoDocumento IS NOT NULL)")
    List<Tipodocumento> findTiposDocumentoSinUsuarios();
    
    // Consulta personalizada para buscar tipos de documento por patrón
    @Query("SELECT t FROM Tipodocumento t WHERE LOWER(t.tipo) LIKE LOWER(CONCAT('%', :patron, '%'))")
    List<Tipodocumento> findByPatronTipo(@Param("patron") String patron);
} 