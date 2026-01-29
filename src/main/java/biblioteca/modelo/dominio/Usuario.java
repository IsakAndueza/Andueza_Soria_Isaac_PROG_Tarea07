package biblioteca.modelo.dominio;

import java.util.Objects;

public class Usuario {
    private static final String DNI_PATTERN = "\\d{8}[A-Z]";
    private String id, nombre, email;
    private Direccion direccion;

    public Usuario(String id, String nombre, String email, Direccion direccion) {
        setId(id);
        setNombre(nombre);
        setEmail(email);
        setDireccion(direccion);
    }

    public Usuario(Usuario u) {
        if (u == null) throw new NullPointerException("ERROR: Usuario nulo.");
        this.id = u.id;
        this.nombre = u.nombre;
        this.email = u.email;
        this.direccion = new Direccion(u.direccion.getVia(), u.direccion.getNumero(), u.direccion.getCp(), u.direccion.getLocalidad());
    }

    public void setId(String id) {
        if (id == null || !id.matches(DNI_PATTERN))
            throw new IllegalArgumentException("ERROR: El DNI debe tener 8 números y una letra mayúscula.");
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("ERROR: El email no es válido.");
        this.email = email;
    }

    public void setDireccion(Direccion direccion) {
        if (direccion == null) throw new NullPointerException("ERROR: La dirección no puede ser nula.");
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario u)) return false;
        return Objects.equals(id, u.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Usuario: %s [DNI: %s]\nEmail: %s | Dirección: %s", nombre, id, email, direccion);
    }
}