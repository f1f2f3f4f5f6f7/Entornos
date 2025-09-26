package tienda.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tipodocumento")
public class Tipodocumento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotBlank(message = "El tipo de documento es obligatorio")
    @Size(max = 50, message = "El tipo de documento no puede exceder 50 caracteres")
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    
    // Relaciones (opcional, para futuras consultas)
    @OneToMany(mappedBy = "idTipoDocumento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
    
    @OneToMany(mappedBy = "idTipoDocumento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cliente> clientes;
    
    // Constructor por defecto
    public Tipodocumento() {
    }
    
    // Constructor con parámetros
    public Tipodocumento(String tipo) {
        this.tipo = tipo;
    }
    
    // Constructor completo
    public Tipodocumento(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    @Override
    public String toString() {
        return "Tipodocumento{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tipodocumento that = (Tipodocumento) o;
        return id != null && id.equals(that.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
