package biblioteca.modelo.dominio;

public enum Categoria {
    NOVELA("Novela"), ENSAYO("Ensayo"), INFANTIL("Infantil"),
    COMIC("Cómic"), POESIA("Poesía"), TECNICO("Técnico"), OTROS("Otros");

    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}