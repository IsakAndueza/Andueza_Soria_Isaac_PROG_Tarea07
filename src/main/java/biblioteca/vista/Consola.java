package biblioteca.vista;

import biblioteca.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class Consola {

    private Consola() {
    }

    public static void mostrarMenu() {
        System.out.println("\n--- MENÚ BIBLIOTECA ---");
        for (Opcion o : Opcion.values()) System.out.println(o);
    }

    public static int elegirOpcion() {
        System.out.print("Elige opción: ");
        return Entrada.entero();
    }

    public static Usuario nuevoUsuario(boolean esBusqueda) {
        String dni = null;
        boolean valido = false;
        do {
            try {
                System.out.print("DNI: ");
                dni = Entrada.cadena();
                new Usuario(dni, "Validando", "valida@test.com", new Direccion("V", "1", "04001", "L"));
                valido = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        if (esBusqueda) return new Usuario(dni, "Busqueda", "b@b.com", new Direccion("V", "1", "04001", "L"));

        String nombre = leerCampoObligatorio("Nombre");
        String email = leerEmail();
        Direccion dir = nuevaDireccion();

        return new Usuario(dni, nombre, email, dir);
    }

    public static Libro nuevoLibro(boolean esBusqueda) {
        String isbn = null;
        boolean valido = false;
        do {
            try {
                System.out.print("ISBN (13 dígitos): ");
                isbn = Entrada.cadena();
                new Libro(isbn, "Validando", 2000, Categoria.OTROS, 1);
                valido = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!valido);

        if (esBusqueda) return new Libro(isbn, "Busqueda", 2000, Categoria.OTROS, 1);

        String titulo = leerCampoObligatorio("Título");
        System.out.print("Año: ");
        int anio = Entrada.entero();

        Categoria cat = null;
        do {
            try {
                System.out.println("Categorías: 0-Novela, 1-Ensayo, 2-Infantil, 3-Comic, 4-Poesia, 5-Tecnico, 6-Otros");
                cat = Categoria.values()[Entrada.entero()];
            } catch (Exception e) {
                System.out.println("ERROR: Categoría no válida.");
            }
        } while (cat == null);

        System.out.print("Unidades: ");
        int uds = Entrada.entero();

        Libro libro = new Libro(isbn, titulo, anio, cat, uds);

        int nAutores;
        do {
            System.out.print("Nº autores (1-3): ");
            nAutores = Entrada.entero();
        } while (nAutores < 1 || nAutores > 3);

        for (int i = 0; i < nAutores; i++) libro.agregarAutor(nuevoAutor());

        return libro;
    }

    private static String leerCampoObligatorio(String campo) {
        String s;
        do {
            System.out.print(campo + ": ");
            s = Entrada.cadena();
        } while (s == null || s.isBlank());
        return s;
    }

    private static String leerEmail() {
        String e;
        do {
            System.out.print("Email: ");
            e = Entrada.cadena();
            if (!e.contains("@")) System.out.println("ERROR: Formato email incorrecto.");
        } while (!e.contains("@"));
        return e;
    }

    public static Direccion nuevaDireccion() {
        String via = leerCampoObligatorio("Vía");
        String num = leerCampoObligatorio("Número");
        String cp = null;
        do {
            try {
                System.out.print("CP (5 dígitos): ");
                cp = Entrada.cadena();
                new Direccion(via, num, cp, "Validando").setCp(cp);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        String loc = leerCampoObligatorio("Localidad");
        return new Direccion(via, num, cp, loc);
    }

    public static Autor nuevoAutor() {
        String n = leerCampoObligatorio("Nombre Autor");
        String a = leerCampoObligatorio("Apellidos Autor");
        System.out.print("Nacionalidad: ");
        String nac = Entrada.cadena();
        return new Autor(n, a, nac);
    }

    public static LocalDate leerFecha() {
        return LocalDate.now();
    }
}