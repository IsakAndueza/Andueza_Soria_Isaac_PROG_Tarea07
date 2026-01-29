package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.*;

import java.time.LocalDate;

public class Prestamos {
    private int capacidad, tamano;
    private Prestamo[] coleccionPrestamos;

    public Prestamos(int capacidad) {
        this.capacidad = capacidad;
        this.coleccionPrestamos = new Prestamo[capacidad];
        this.tamano = 0;
    }

    public void prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        if (tamano >= capacidad) throw new IllegalStateException("ERROR: No hay capacidad para más préstamos.");
        libro.tomarPrestado();

        coleccionPrestamos[tamano++] = new Prestamo(libro, usuario, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        for (int i = 0; i < tamano; i++) {
            Prestamo p = coleccionPrestamos[i];
            if (!p.isDevuelto() && p.getLibro().equals(libro) && p.getUsuario().equals(usuario)) {
                p.marcarDevuelto(fecha);
                libro.devolverUnidad();
                return true;
            }
        }
        return false;
    }

    public Prestamo[] historial() {
        Prestamo[] copia = new Prestamo[tamano];
        for (int i = 0; i < tamano; i++) copia[i] = new Prestamo(coleccionPrestamos[i]);
        return copia;
    }

    public Prestamo[] prestamosUsuario(Usuario u) {
        int count = 0;
        for (int i = 0; i < tamano; i++) if (coleccionPrestamos[i].getUsuario().equals(u)) count++;
        Prestamo[] res = new Prestamo[count];
        int j = 0;
        for (int i = 0; i < tamano; i++)
            if (coleccionPrestamos[i].getUsuario().equals(u)) res[j++] = new Prestamo(coleccionPrestamos[i]);
        return res;
    }
}