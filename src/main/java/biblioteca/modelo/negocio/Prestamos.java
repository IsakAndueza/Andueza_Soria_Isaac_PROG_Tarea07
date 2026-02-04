package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Prestamos {
    private List<Prestamo> coleccionPrestamos;

    public Prestamos() {
        coleccionPrestamos = new ArrayList<>();
    }

    public void prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        if (libro == null || usuario == null) {
            throw new NullPointerException("ERROR: No se puede prestar un libro o usuario nulo.");
        }
        for (Prestamo p : coleccionPrestamos) {
            if (!p.isDevuelto() && p.getLibro().equals(libro) && p.getUsuario().equals(usuario)) {
                throw new IllegalStateException("ERROR: Este usuario ya tiene un préstamo activo de este libro.");
            }
        }
        libro.tomarPrestado();
        coleccionPrestamos.add(new Prestamo(libro, usuario, fecha));
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        for (Prestamo p : coleccionPrestamos) {
            if (!p.isDevuelto() && p.getLibro().equals(libro) && p.getUsuario().equals(usuario)) {
                p.marcarDevuelto(fecha);
                libro.devolverUnidad();
                return true;
            }
        }
        return false;
    }

    public List<Prestamo> todos() {
        List<Prestamo> copiaSorted = new ArrayList<>();
        for (Prestamo p : coleccionPrestamos) {
            copiaSorted.add(new Prestamo(p));
        }
        ordenarLista(copiaSorted);
        return copiaSorted;
    }

    public List<Prestamo> todos(Usuario usuario) {
        if (usuario == null) {
            throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
        }
        List<Prestamo> filtrados = new ArrayList<>();
        for (Prestamo p : coleccionPrestamos) {
            if (p.getUsuario().equals(usuario)) {
                filtrados.add(new Prestamo(p));
            }
        }
        ordenarLista(filtrados);
        return filtrados;
    }

    private void ordenarLista(List<Prestamo> lista) {
        lista.sort(Comparator.comparing(Prestamo::getfInicio).reversed()
                .thenComparing(p -> p.getUsuario().getNombre()));
    }
}