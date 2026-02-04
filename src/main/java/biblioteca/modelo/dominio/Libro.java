package biblioteca.modelo.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libro {
    private static final String ISBN_PATTERN = "\\d{13}";
    private String isbn, titulo;
    private int anio, unidades;
    private Categoria categoria;
    private List<Autor> autores;

    public Libro(String isbn, String titulo, int anio, Categoria categoria, int unidades) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAnio(anio);
        setCategoria(categoria);
        setUnidades(unidades);
        this.autores = new ArrayList<>();
    }

    public Libro(Libro l) {
        if (l == null) throw new NullPointerException("ERROR: Libro nulo.");
        this.isbn = l.isbn;
        this.titulo = l.titulo;
        this.anio = l.anio;
        this.categoria = l.categoria;
        this.unidades = l.unidades;
        this.autores = new ArrayList<>();
        for (Autor a : l.autores) this.autores.add(new Autor(a));
    }

    private void setIsbn(String isbn) {
        if (isbn == null || !isbn.matches(ISBN_PATTERN))
            throw new IllegalArgumentException("ERROR: ISBN de 13 dígitos.");
        this.isbn = isbn;
    }

    private void setTitulo(String t) {
        if (t == null || t.isBlank()) throw new IllegalArgumentException("ERROR: Título vacío.");
        this.titulo = t;
    }

    private void setAnio(int a) {
        this.anio = a;
    }

    private void setCategoria(Categoria c) {
        if (c == null) throw new NullPointerException("ERROR: Categoría nula.");
        this.categoria = c;
    }

    private void setUnidades(int u) {
        if (u < 0) throw new IllegalArgumentException("ERROR: Unidades negativas.");
        this.unidades = u;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void agregarAutor(Autor a) {
        if (a == null) throw new NullPointerException("ERROR: Autor nulo.");
        autores.add(new Autor(a));
    }

    public void tomarPrestado() {
        if (unidades <= 0) throw new IllegalStateException("ERROR: Sin stock.");
        unidades--;
    }

    public void devolverUnidad() {
        unidades++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro l)) return false;
        return Objects.equals(isbn, l.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("LIBRO -> Título: %s | ISBN: %s | Unidades: %d | Categoría: %s | Autores: %s",
                titulo, isbn, unidades, categoria, autores);
    }
}