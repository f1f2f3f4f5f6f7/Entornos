package tienda.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @NotNull(message = "El tipo de documento es obligatorio")
    @Column(name = "idtipodocumento", nullable = false)
    private Integer idTipoDocumento;
    
    @NotBlank(message = "El número de documento es obligatorio")
    @Size(max = 40, message = "El número de documento no puede exceder 40 caracteres")
    @Column(name = "numerodocumento", nullable = false, length = 40)
    private String numeroDocumento;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 255, message = "La contraseña no puede exceder 255 caracteres")
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 255, message = "El nombre de usuario no puede exceder 255 caracteres")
    @Column(name = "nombreusuario", nullable = false, length = 255)
    private String nombreUsuario;
    
    // Constructor por defecto
    public Usuario() {
    }
    
    // Constructor con todos los parámetros
    public Usuario(Integer idTipoDocumento, String numeroDocumento, String email, 
                   String nombre, String password, String nombreUsuario) {
        this.idTipoDocumento = idTipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
    }
    
    // Getters y Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }
    
    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
    
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", idTipoDocumento=" + idTipoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", password='[PROTEGIDO]'" +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }
}