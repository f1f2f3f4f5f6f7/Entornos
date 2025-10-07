package tienda.controlador;

import tienda.modelo.Usuario;

public class JwtResponse {
    private String token;
    private Integer id;
    private String nombreUsuario;
    private String email;
    private String nombre;

    public JwtResponse(String token, Usuario u) {
        this.token = token;
        this.id = u.getId();
        this.nombreUsuario = u.getNombreUsuario();
        this.email = u.getEmail();
        this.nombre = u.getNombre();
    }

    public String getToken() { return token; }
    public Integer getId() { return id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
}