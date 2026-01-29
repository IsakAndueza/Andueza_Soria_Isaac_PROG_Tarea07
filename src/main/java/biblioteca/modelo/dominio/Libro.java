package biblioteca.modelo.dominio;

import java.util.Objects;

public class Libro {
    private static final String ISBN_PATTERN = "\\d{13}";
    private static final int MAX_AUTORES = 3;
    private String isbn, titulo;
    private int anio, unidades;
    private Categoria categoria;
    private Autor[] autores;
    private int numAutores;

    public Libro(String isbn, String titulo, int anio, Categoria categoria, int unidades) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAnio(anio);
        setCategoria(categoria);
        setUnidades(unidades);
        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = 0;
    }

    public Libro(Libro libro) {
        if (libro == null) throw new NullPointerException("ERROR: No se puede copiar un libro nulo.");
        this.isbn = libro.isbn;
        this.titulo = libro.titulo;
        this.anio = libro.anio;
        this.categoria = libro.categoria;
        this.unidades = libro.unidades;
        this.numAutores = libro.numAutores;
        this.autores = new Autor[MAX_AUTORES];
        for (int i = 0; i < numAutores; i++) {
            this.autores[i] = new Autor(libro.autores[i]);
        }
    }

    public void tomarPrestado() {
        if (unidades <= 0) {
            throw new IllegalStateException("ERROR: No quedan unidades disponibles de este libro.");
        }
        unidades--;
    }

    public void devolverUnidad() {
        unidades++;
    }

    private void setIsbn(String isbn) {
        if (isbn == null || !isbn.matches(ISBN_PATTERN))
            throw new IllegalArgumentException("ERROR: ISBN no válido (deben ser 13 dígitos).");
        this.isbn = isbn;
    }

    private void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
        this.titulo = titulo;
    }

    private void setAnio(int anio) {
        this.anio = anio;
    }

    private void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private void setUnidades(int unidades) {
        if (unidades < 0) throw new IllegalArgumentException("ERROR: Las unidades no pueden ser negativas.");
        this.unidades = unidades;
    }

    public void agregarAutor(Autor autor) {
        if (autor == null) throw new NullPointerException("ERROR: Autor nulo.");
        if (numAutores >= MAX_AUTORES) throw new IllegalArgumentException("ERROR: Máximo 3 autores.");
        autores[numAutores++] = new Autor(autor);
    }

    public String getIsbn() {
        return isbn;
    }

    public int getUnidades() {
        return unidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro libro)) return false;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Libro -> ISBN: %s, Título: %s, Año: %d, Categoría: %s, Unidades: %d\n",
                isbn, titulo, anio, categoria, unidades));
        sb.append("   Autores: ");
        for (int i = 0; i < numAutores; i++) {
            sb.append(autores[i]).append(i == numAutores - 1 ? "" : " | ");
        }
        return sb.toString();
    }
}