
Flujo de autenticación (JWT)
El cliente hace POST /auth/login con nombreUsuario y password.
Si las credenciales son válidas, se genera un JWT con HS256 y se devuelve junto con datos del usuario.
En peticiones posteriores, se envía el header Authorization: Bearer <token>.
Un filtro (JwtAuthenticationFilter) valida el token y establece la autenticación con ROLE_USER.
La app es stateless: no hay sesión en servidor.
Endpoints principales
Auth
POST /auth/login: login. Body: { "nombreUsuario", "password" }. Respuesta: { token, id, nombreUsuario, email, nombre }.
POST /auth/registro: registra un Usuario nuevo (hash de password con BCrypt).
Usuarios (/api/usuarios, requiere JWT)
GET /api/usuarios: lista usuarios.
GET /api/usuarios/{id}: detalle.
POST /api/usuarios: crear.
PUT /api/usuarios/{id}: actualizar.
DELETE /api/usuarios/{id}: eliminar.
Tipos de documento (/api/tipos-documento, requiere JWT)
Entidades
Usuario: id, idTipoDocumento, numeroDocumento, email, nombre, password (BCrypt), nombreUsuario.
Tipodocumento: id, tipo. Relaciones 1-N (mapeadas) con Usuario y Cliente.
Cliente: campos básicos de identificación y contacto (no expuesto por controladores en este proyecto).
Servicios
UsuarioServicio
CRUD completo, validaciones de unicidad (nombreUsuario, email), hashing de contraseña.
autenticarUsuario(nombreUsuario, password): compara con BCrypt y retorna Optional<Usuario>.
TipoDocumentoServicio
CRUD + búsquedas y estadísticas, validaciones de unicidad y reglas para eliminación segura.
Repositorios (Spring Data JPA)
UsuarioRepositorio: consultas por nombreUsuario, email, numeroDocumento, búsquedas parciales y conteos por idTipoDocumento.
Seguridad y configuración
SecurityConfig
CORS abierto (*), CSRF deshabilitado, sesiones STATELESS.
Permite /auth/**, /api-docs/**, /swagger-ui.html, /swagger-ui/**.
Inserta JwtAuthenticationFilter antes de UsernamePasswordAuthenticationFilter.
JwtService
Lee app.jwt.secret (Base64) y app.jwt.expiration-millis desde application.properties.
Genera, valida y parsea tokens (subject = nombreUsuario).
JwtAuthenticationFilter
Extrae Bearer token, valida, y establece autenticación con ROLE_USER.
Configuración de entorno
application.properties
Puerto: server.port=8094.
PostgreSQL (Supabase) por URL, usuario y contraseña.
Hibernate: ddl-auto=validate, show-sql=true.
JWT: app.jwt.secret (Base64), app.jwt.expiration-millis=86400000.
