package tienda.controlador;

import tienda.modelo.Tipodocumento;
import tienda.servicio.TipoDocumentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-documento")
@CrossOrigin(origins = "*")
public class TipoDocumentoControlador {
    
    @Autowired
    private TipoDocumentoServicio tipoDocumentoServicio;
    
    // Obtener todos los tipos de documento
    @GetMapping
    public ResponseEntity<List<Tipodocumento>> obtenerTodosTiposDocumento() {
        try {
            List<Tipodocumento> tiposDocumento = tipoDocumentoServicio.obtenerTodosTiposDocumento();
            return ResponseEntity.ok(tiposDocumento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener todos los tipos de documento ordenados
    @GetMapping("/ordenados")
    public ResponseEntity<List<Tipodocumento>> obtenerTodosTiposDocumentoOrdenados() {
        try {
            List<Tipodocumento> tiposDocumento = tipoDocumentoServicio.obtenerTodosTiposDocumentoOrdenados();
            return ResponseEntity.ok(tiposDocumento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener tipo de documento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tipodocumento> obtenerTipoDocumentoPorId(@PathVariable Integer id) {
        try {
            Optional<Tipodocumento> tipoDocumento = tipoDocumentoServicio.obtenerTipoDocumentoPorId(id);
            return tipoDocumento.map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener tipo de documento por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<Tipodocumento> obtenerTipoDocumentoPorTipo(@PathVariable String tipo) {
        try {
            Optional<Tipodocumento> tipoDocumento = tipoDocumentoServicio.obtenerTipoDocumentoPorTipo(tipo);
            return tipoDocumento.map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener tipo de documento por tipo (insensible a mayúsculas/minúsculas)
    @GetMapping("/tipo-ignore-case/{tipo}")
    public ResponseEntity<Tipodocumento> obtenerTipoDocumentoPorTipoIgnoreCase(@PathVariable String tipo) {
        try {
            Optional<Tipodocumento> tipoDocumento = tipoDocumentoServicio.obtenerTipoDocumentoPorTipoIgnoreCase(tipo);
            return tipoDocumento.map(ResponseEntity::ok)
                               .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Crear nuevo tipo de documento
    @PostMapping
    public ResponseEntity<?> crearTipoDocumento(@Valid @RequestBody Tipodocumento tipoDocumento) {
        try {
            tipoDocumentoServicio.validarTipoDocumento(tipoDocumento);
            Tipodocumento tipoDocumentoCreado = tipoDocumentoServicio.crearTipoDocumento(tipoDocumento);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoDocumentoCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Actualizar tipo de documento
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTipoDocumento(@PathVariable Integer id, @Valid @RequestBody Tipodocumento tipoDocumento) {
        try {
            Tipodocumento tipoDocumentoActualizado = tipoDocumentoServicio.actualizarTipoDocumento(id, tipoDocumento);
            return ResponseEntity.ok(tipoDocumentoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Eliminar tipo de documento
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTipoDocumento(@PathVariable Integer id) {
        try {
            // Verificar si se puede eliminar
            if (!tipoDocumentoServicio.puedeEliminarTipoDocumento(id)) {
                return ResponseEntity.badRequest()
                                   .body("No se puede eliminar el tipo de documento porque tiene usuarios asociados");
            }
            
            tipoDocumentoServicio.eliminarTipoDocumento(id);
            return ResponseEntity.ok("Tipo de documento eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body("Error interno del servidor");
        }
    }
    
    // Verificar existencia de tipo de documento por tipo
    @GetMapping("/existe/tipo/{tipo}")
    public ResponseEntity<Boolean> existeTipoDocumentoPorTipo(@PathVariable String tipo) {
        try {
            boolean existe = tipoDocumentoServicio.existeTipoDocumentoPorTipo(tipo);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Verificar existencia de tipo de documento por tipo (insensible a mayúsculas/minúsculas)
    @GetMapping("/existe/tipo-ignore-case/{tipo}")
    public ResponseEntity<Boolean> existeTipoDocumentoPorTipoIgnoreCase(@PathVariable String tipo) {
        try {
            boolean existe = tipoDocumentoServicio.existeTipoDocumentoPorTipoIgnoreCase(tipo);
            return ResponseEntity.ok(existe);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Buscar tipos de documento por texto
    @GetMapping("/buscar")
    public ResponseEntity<List<Tipodocumento>> buscarTiposDocumentoPorTexto(@RequestParam String texto) {
        try {
            List<Tipodocumento> tiposDocumento = tipoDocumentoServicio.buscarTiposDocumentoPorTexto(texto);
            return ResponseEntity.ok(tiposDocumento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Buscar tipos de documento por patrón
    @GetMapping("/buscar-patron")
    public ResponseEntity<List<Tipodocumento>> buscarTiposDocumentoPorPatron(@RequestParam String patron) {
        try {
            List<Tipodocumento> tiposDocumento = tipoDocumentoServicio.buscarTiposDocumentoPorPatron(patron);
            return ResponseEntity.ok(tiposDocumento);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener estadísticas de usuarios por tipo de documento
    @GetMapping("/estadisticas")
    public ResponseEntity<List<Object[]>> obtenerEstadisticasUsuariosPorTipoDocumento() {
        try {
            List<Object[]> estadisticas = tipoDocumentoServicio.obtenerEstadisticasUsuariosPorTipoDocumento();
            return ResponseEntity.ok(estadisticas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener tipos de documento más utilizados
    @GetMapping("/mas-utilizados")
    public ResponseEntity<List<Object[]>> obtenerTiposDocumentoMasUtilizados() {
        try {
            List<Object[]> masUtilizados = tipoDocumentoServicio.obtenerTiposDocumentoMasUtilizados();
            return ResponseEntity.ok(masUtilizados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Obtener tipos de documento sin usuarios
    @GetMapping("/sin-usuarios")
    public ResponseEntity<List<Tipodocumento>> obtenerTiposDocumentoSinUsuarios() {
        try {
            List<Tipodocumento> tiposSinUsuarios = tipoDocumentoServicio.obtenerTiposDocumentoSinUsuarios();
            return ResponseEntity.ok(tiposSinUsuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // Verificar si se puede eliminar un tipo de documento
    @GetMapping("/puede-eliminar/{id}")
    public ResponseEntity<Boolean> puedeEliminarTipoDocumento(@PathVariable Integer id) {
        try {
            boolean puedeEliminar = tipoDocumentoServicio.puedeEliminarTipoDocumento(id);
            return ResponseEntity.ok(puedeEliminar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 




