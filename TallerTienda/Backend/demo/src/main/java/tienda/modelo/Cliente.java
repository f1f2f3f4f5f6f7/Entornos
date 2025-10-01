package tienda.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull
    @Column(name = "idtipodocumento", nullable = false)
    private Integer idTipoDocumento;
    
    @NotBlank
    @Size(max = 30)
    @Column(name = "numerodocumento", nullable = false, length = 30)
    private String numeroDocumento;
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;
    
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    
    @NotBlank
    @Size(max = 200)
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    
    // Constructores, getters y setters...
    public Cliente() {}
    
    // Getters y setters básicos
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdTipoDocumento() { return idTipoDocumento; }
    public void setIdTipoDocumento(Integer idTipoDocumento) { this.idTipoDocumento = idTipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
} 