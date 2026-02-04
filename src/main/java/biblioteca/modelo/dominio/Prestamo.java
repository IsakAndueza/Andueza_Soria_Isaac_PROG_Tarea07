package biblioteca.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate fInicio;
    private LocalDate fLimite;
    private LocalDate fDevolucion;
    private boolean devuelto;
    private static final int DURACION = 15;

    public Prestamo(Libro libro, Usuario usuario, LocalDate fInicio) {
        if (libro == null || usuario == null || fInicio == null) {
            throw new NullPointerException("ERROR: Datos nulos.");
        }
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

    public LocalDate getfInicio() {
        return fInicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void marcarDevuelto(LocalDate fecha) {
        if (fecha == null) throw new NullPointerException("ERROR: Fecha nula.");
        if (fecha.isBefore(fInicio)) throw new IllegalArgumentException("ERROR: Fecha incoherente.");
        this.fDevolucion = fecha;
        this.devuelto = true;
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
        String info = String.format("PRÉSTAMO -> Libro: %s | Usuario: %s | Inicio: %s | Límite: %s",
                libro.getTitulo(), usuario.getNombre(), fInicio.format(dtf), fLimite.format(dtf));
        if (devuelto) {
            info += " | Devuelto el: " + fDevolucion.format(dtf);
        } else {
            info += " | PENDIENTE";
        }
        return info;
    }
}