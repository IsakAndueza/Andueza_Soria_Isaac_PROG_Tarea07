package biblioteca.vista;

public enum Opcion {
    SALIR("Salir"),
    INSERTAR_USUARIO("Insertar usuario"),
    BORRAR_USUARIO("Borrar usuario"),
    MOSTRAR_USUARIOS("Mostrar Usuarios"),
    INSERTAR_LIBRO("Insertar libro"),
    BORRAR_LIBRO("Borrar Libro"),
    MOSTRAR_LIBROS("Mostrar Libros"),
    NUEVO_PRESTAMO("Nuevo Prestamo"),
    DEVOLVER_PRESTAMO("Devolver préstamo"),
    MOSTRAR_PRESTAMOS("Mostrar Todos los Préstamos"),
    MOSTRAR_PRESTAMOS_USUARIO("Mostrar los Préstamos de un usuario");

    private final String mensaje;

    Opcion(String mensaje) {
        this.mensaje = mensaje;
    }

    public static Opcion getOpcionSegunOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IllegalArgumentException("ERROR: Opción no válida (0-10).");
        }
        return values()[ordinal];
    }

    @Override
    public String toString() {
        return String.format("%d - %s", ordinal(), mensaje);
    }
}