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
        if (id == null || !id.matches(DNI_PATTERN)) {
            throw new IllegalArgumentException("ERROR: El DNI debe tener 8 números y una letra mayúscula.");
        }
        this.id = id;
    }

    public void setNombre(String n) {
        if (n == null || n.isBlank()) throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
        this.nombre = n;
    }

    public void setEmail(String e) {
        if (e == null || !e.contains("@")) {
            throw new IllegalArgumentException("ERROR: El formato del email no es válido (debe contener '@').");
        }
        this.email = e;
    }

    public void setDireccion(Direccion d) {
        if (d == null) throw new NullPointerException("ERROR: La dirección no puede ser nula.");
        this.direccion = d;
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
        return String.format("USUARIO -> Nombre: %s | DNI: %s | Email: %s | Dirección: %s", nombre, id, email, direccion);
    }
}