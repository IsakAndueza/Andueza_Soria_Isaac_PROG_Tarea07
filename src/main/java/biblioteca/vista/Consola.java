package biblioteca.vista;

import biblioteca.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {
        System.out.println("\n--- MENÚ BIBLIOTECA TAREA 7 ---");
        for (Opcion o : Opcion.values()) System.out.println(o);
    }

    public static int elegirOpcion() {
        System.out.print("Elige una opción: ");
        return Entrada.entero();
    }

    public static Usuario nuevoUsuario(boolean esBusqueda) {
        String dni = leerDni();
        if (esBusqueda) return new Usuario(dni, "Busqueda", "b@b.com", new Direccion("V", "1", "04001", "L"));

        String nombre = leerCampo("Nombre");
        String email = leerEmail();
        Direccion dir = nuevaDireccion();
        return new Usuario(dni, nombre, email, dir);
    }

    public static Libro nuevoLibro(boolean esBusqueda) {
        String isbn = leerIsbn();
        if (esBusqueda) return new Libro(isbn, "Busqueda", 2000, Categoria.OTROS, 1);

        String titulo = leerCampo("Título");
        System.out.print("Año: ");
        int anio = Entrada.entero();
        Categoria cat = leerCategoria();
        System.out.print("Unidades: ");
        int uds = Entrada.entero();

        Libro libro = new Libro(isbn, titulo, anio, cat, uds);

        System.out.print("¿Cuántos autores quieres añadir?: ");
        int nAutores = Entrada.entero();
        for (int i = 0; i < nAutores; i++) {
            System.out.println("Autor " + (i + 1) + ":");
            libro.agregarAutor(nuevoAutor());
        }
        return libro;
    }

    public static Autor nuevoAutor() {
        String n = leerCampo("Nombre Autor");
        String a = leerCampo("Apellidos Autor");
        System.out.print("Nacionalidad: ");
        String nac = Entrada.cadena();
        return new Autor(n, a, nac);
    }

    private static String leerDni() {
        String dni;
        do {
            try {
                System.out.print("Introduce DNI (8 dígitos y letra): ");
                dni = Entrada.cadena();
                new Usuario(dni, "V", "v@v.com", new Direccion("V", "1", "04001", "L"));
                return dni;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static String leerIsbn() {
        String isbn;
        do {
            try {
                System.out.print("Introduce ISBN (13 dígitos): ");
                isbn = Entrada.cadena();
                new Libro(isbn, "V", 2000, Categoria.OTROS, 1);
                return isbn;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private static String leerCampo(String c) {
        String s;
        do {
            System.out.print(c + ": ");
            s = Entrada.cadena();
            if (s == null || s.isBlank()) {
                System.out.println("ERROR: El campo " + c + " no puede estar vacío.");
            }
        } while (s == null || s.isBlank());
        return s;
    }

    private static String leerEmail() {
        String e;
        do {
            try {
                System.out.print("Email: ");
                e = Entrada.cadena();
                if (e == null || !e.contains("@")) {
                    throw new IllegalArgumentException("ERROR: El formato del email no es válido (debe contener '@').");
                }
                return e;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }

    private static Categoria leerCategoria() {
        do {
            try {
                System.out.println("Categorías: 0-Novela, 1-Ensayo, 2-Infantil, 3-Comic, 4-Poesia, 5-Tecnico, 6-Otros");
                return Categoria.values()[Entrada.entero()];
            } catch (Exception e) {
                System.out.println("ERROR: Categoría no válida.");
            }
        } while (true);
    }

    public static Direccion nuevaDireccion() {
        String v = leerCampo("Vía");
        String n = leerCampo("Número");
        String cp;
        do {
            try {
                System.out.print("CP (5 dígitos): ");
                cp = Entrada.cadena();
                new Direccion(v, n, cp, "L");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return new Direccion(v, n, cp, leerCampo("Localidad"));
    }

    public static LocalDate leerFecha() {
        return LocalDate.now();
    }
}