package tienda.servicio;

import tienda.modelo.Tipodocumento;
import tienda.repositorio.TipoDocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;




@Service
@Transactional
public class TipoDocumentoServicio {
    
    @Autowired
    private TipoDocumentoRepositorio tipoDocumentoRepositorio;
    
    // Obtener todos los tipos de documento
    public List<Tipodocumento> obtenerTodosTiposDocumento() {
        return tipoDocumentoRepositorio.findAll();
    }
    
    // Obtener todos los tipos de documento ordenados por tipo
    public List<Tipodocumento> obtenerTodosTiposDocumentoOrdenados() {
        return tipoDocumentoRepositorio.findAllOrderByTipo();
    }
    
    public Optional<Tipodocumento> obtenerTipoDocumentoPorId(Integer id) {
        return tipoDocumentoRepositorio.findById(id);
    }
    
    // Obtener tipo de documento por tipo exacto
    public Optional<Tipodocumento> obtenerTipoDocumentoPorTipo(String tipo) {
        return tipoDocumentoRepositorio.findByTipo(tipo);
    }
    
    // Obtener tipo de documento por tipo (insensible a mayúsculas/minúsculas)
    public Optional<Tipodocumento> obtenerTipoDocumentoPorTipoIgnoreCase(String tipo) {
        return tipoDocumentoRepositorio.findByTipoIgnoreCase(tipo);
    }
    
    // Guardar tipo de documento (crear o actualizar)
    public Tipodocumento guardarTipoDocumento(Tipodocumento tipoDocumento) {
        return tipoDocumentoRepositorio.save(tipoDocumento);
    }
    
    // Crear nuevo tipo de documento
    public Tipodocumento crearTipoDocumento(Tipodocumento tipoDocumento) {
        // Validar que no exista un tipo de documento con el mismo tipo
        if (tipoDocumentoRepositorio.existsByTipoIgnoreCase(tipoDocumento.getTipo())) {
            throw new RuntimeException("Ya existe un tipo de documento con el tipo: " + tipoDocumento.getTipo());
        }
        
        return tipoDocumentoRepositorio.save(tipoDocumento);
    }
    
    // Actualizar tipo de documento
    public Tipodocumento actualizarTipoDocumento(Integer id, Tipodocumento tipoDocumentoActualizado) {
        Tipodocumento tipoDocumentoExistente = tipoDocumentoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado con ID: " + id));
        
        // Validar unicidad del tipo si cambió
        if (!tipoDocumentoExistente.getTipo().equalsIgnoreCase(tipoDocumentoActualizado.getTipo()) &&
            tipoDocumentoRepositorio.existsByTipoIgnoreCase(tipoDocumentoActualizado.getTipo())) {
            throw new RuntimeException("Ya existe un tipo de documento con el tipo: " + tipoDocumentoActualizado.getTipo());
        }
        
        // Actualizar campo
        tipoDocumentoExistente.setTipo(tipoDocumentoActualizado.getTipo());
        
        return tipoDocumentoRepositorio.save(tipoDocumentoExistente);
    }
    
    // Eliminar tipo de documento por ID
    public void eliminarTipoDocumento(Integer id) {
        if (!tipoDocumentoRepositorio.existsById(id)) {
            throw new RuntimeException("Tipo de documento no encontrado con ID: " + id);
        }
        tipoDocumentoRepositorio.deleteById(id);
    }
    
    // Verificar si existe un tipo de documento con el tipo dado
    public boolean existeTipoDocumentoPorTipo(String tipo) {
        return tipoDocumentoRepositorio.existsByTipo(tipo);
    }
    
    // Verificar si existe un tipo de documento con el tipo dado (insensible a mayúsculas/minúsculas)
    public boolean existeTipoDocumentoPorTipoIgnoreCase(String tipo) {
        return tipoDocumentoRepositorio.existsByTipoIgnoreCase(tipo);
    }
    
    // Buscar tipos de documento que contengan un texto específico
    public List<Tipodocumento> buscarTiposDocumentoPorTexto(String texto) {
        return tipoDocumentoRepositorio.findByTipoContainingIgnoreCase(texto);
    }
    
    // Buscar tipos de documento por patrón
    public List<Tipodocumento> buscarTiposDocumentoPorPatron(String patron) {
        return tipoDocumentoRepositorio.findByPatronTipo(patron);
    }
    
    // Obtener estadísticas de usuarios por tipo de documento
    public List<Object[]> obtenerEstadisticasUsuariosPorTipoDocumento() {
        return tipoDocumentoRepositorio.countUsuariosByTipoDocumento();
    }
    
    // Obtener tipos de documento que no tienen usuarios asociados
    public List<Tipodocumento> obtenerTiposDocumentoSinUsuarios() {
        return tipoDocumentoRepositorio.findTiposDocumentoSinUsuarios();
    }
    
    // Validar datos del tipo de documento antes de guardar
    public void validarTipoDocumento(Tipodocumento tipoDocumento) {
        if (tipoDocumento.getTipo() == null || tipoDocumento.getTipo().trim().isEmpty()) {
            throw new RuntimeException("El tipo de documento es obligatorio");
        }
        if (tipoDocumento.getTipo().length() > 50) {
            throw new RuntimeException("El tipo de documento no puede exceder 50 caracteres");
        }
    }
    
    // Obtener tipos de documento más utilizados
    public List<Object[]> obtenerTiposDocumentoMasUtilizados() {
        List<Object[]> estadisticas = tipoDocumentoRepositorio.countUsuariosByTipoDocumento();
        return estadisticas.stream()
                .sorted((a, b) -> Long.compare((Long) b[1], (Long) a[1]))
                .toList();
    }
    
    // Verificar si un tipo de documento puede ser eliminado (no tiene usuarios asociados)
    public boolean puedeEliminarTipoDocumento(Integer id) {
        return tipoDocumentoRepositorio.countUsuariosByTipoDocumento().stream()
                .noneMatch(estadistica -> estadistica[0].equals(tipoDocumentoRepositorio.findById(id).orElse(null).getTipo()));
    }
} 
