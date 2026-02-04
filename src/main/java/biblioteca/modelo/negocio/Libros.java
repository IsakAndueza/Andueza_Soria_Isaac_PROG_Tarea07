package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Libros {
    private List<Libro> coleccionLibros;

    public Libros() {
        coleccionLibros = new ArrayList<>();
    }

    public void alta(Libro libro) {
        if (libro == null) {
            throw new NullPointerException("ERROR: No se puede dar de alta un libro nulo.");
        }
        if (coleccionLibros.contains(libro)) {
            throw new IllegalStateException("ERROR: Ya existe un libro con el mismo ISBN.");
        }
        coleccionLibros.add(new Libro(libro));
    }

    public void baja(Libro libro) {
        if (libro == null) {
            throw new NullPointerException("ERROR: No se puede dar de baja un libro nulo.");
        }
        if (!coleccionLibros.contains(libro)) {
            throw new IllegalStateException("ERROR: El libro no existe.");
        }
        coleccionLibros.remove(libro);
    }

    public Libro buscar(Libro libro) {
        if (libro == null) {
            return null;
        }
        int indice = coleccionLibros.indexOf(libro);
        if (indice != -1) {
            return coleccionLibros.get(indice);
        }
        return null;
    }

    public List<Libro> todos() {
        List<Libro> copiaSorted = new ArrayList<>();
        for (Libro l : coleccionLibros) {
            copiaSorted.add(new Libro(l));
        }

        copiaSorted.sort(Comparator.comparing(Libro::getTitulo));
        return copiaSorted;
    }
}