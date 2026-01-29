package biblioteca.modelo.dominio;

public record Autor(String nombre, String apellidos, String nacionalidad) {
    public Autor {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo.");
        if (apellidos == null || apellidos.isBlank())
            throw new IllegalArgumentException("ERROR: Los apellidos no pueden ser nulos.");
    }

    public Autor(Autor autor) {
        this(autor.nombre, autor.apellidos, autor.nacionalidad);
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", nombre, apellidos, nacionalidad);
    }
}