package biblioteca.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fInicio, fLimite, fDevolucion;
    private boolean devuelto;
    private static final int DURACION = 15;

    public Prestamo(Libro libro, Usuario usuario, LocalDate fInicio) {
        if (libro == null || usuario == null || fInicio == null) throw new NullPointerException("ERROR: Datos nulos.");
        this.libro = new Libro(libro);
        this.usuario = new Usuario(usuario);
        this.fInicio = fInicio;
        this.fLimite = fInicio.plusDays(DURACION);
        this.devuelto = false;
    }

    public Prestamo(Prestamo p) {
        if (p == null) throw new NullPointerException("ERROR: Préstamo nulo.");
        this.libro = new Libro(p.libro);
        this.usuario = new Usuario(p.usuario);
        this.fInicio = p.fInicio;
        this.fLimite = p.fLimite;
        this.fDevolucion = p.fDevolucion;
        this.devuelto = p.devuelto;
    }

    public void marcarDevuelto(LocalDate fecha) {
        if (fecha == null) throw new NullPointerException("ERROR: Fecha nula.");
        if (fecha.isBefore(fInicio))
            throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser anterior al inicio.");
        this.fDevolucion = fecha;
        this.devuelto = true;
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestamo p)) return false;
        return Objects.equals(libro, p.libro) && Objects.equals(usuario, p.usuario) && Objects.equals(fInicio, p.fInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libro, usuario, fInicio);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String estado = devuelto ? "Devuelto el " + fDevolucion.format(dtf) : "Pendiente (Límite: " + fLimite.format(dtf) + ")";
        return String.format("PRÉSTAMO -> Usuario: %s | Libro: %s | Inicio: %s | %s",
                usuario.getNombre(), libro.getIsbn(), fInicio.format(dtf), estado);
    }
}