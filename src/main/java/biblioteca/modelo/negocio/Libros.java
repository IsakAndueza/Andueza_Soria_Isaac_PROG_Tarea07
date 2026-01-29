package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;

import java.util.Arrays;

public class Libros {
    private int capacidad;
    private int tamano;
    private Libro[] coleccionLibros;

    public Libros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser un valor positivo.");
        }
        this.capacidad = capacidad;
        this.coleccionLibros = new Libro[capacidad];
        this.tamano = 0;
    }

    public void alta(Libro libro) {
        if (libro == null) {
            throw new NullPointerException("ERROR: No se puede dar de alta un libro nulo.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No hay espacio para más libros en la biblioteca.");
        }
        if (buscar(libro) != null) {
            throw new IllegalStateException("ERROR: Ya existe un libro con el mismo ISBN.");
        }
        coleccionLibros[tamano++] = new Libro(libro);
    }

    public boolean baja(Libro libro) {
        if (libro == null) return false;
        for (int i = 0; i < tamano; i++) {
            if (coleccionLibros[i].equals(libro)) {
                for (int j = i; j < tamano - 1; j++) {
                    coleccionLibros[j] = coleccionLibros[j + 1];
                }
                coleccionLibros[--tamano] = null;
                return true;
            }
        }
        return false;
    }

    public Libro buscar(Libro libro) {
        if (libro == null) return null;
        for (int i = 0; i < tamano; i++) {
            if (coleccionLibros[i].equals(libro)) {
                return coleccionLibros[i];
            }
        }
        return null;
    }

    public Libro[] todos() {

        Libro[] copia = new Libro[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Libro(coleccionLibros[i]);
        }
        return copia;
    }
}