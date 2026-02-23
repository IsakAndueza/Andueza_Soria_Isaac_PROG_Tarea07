package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;

import java.util.Comparator;
import java.util.List;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador != null) this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion = null;
        do {
            try {
                Consola.mostrarMenu();
                opcion = Opcion.getOpcionSegunOrdinal(Consola.elegirOpcion());
                ejecutarOpcion(opcion);
            } catch (Exception e) {
                System.out.println("\n>>> " + e.getMessage());
            }
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Termina Vista");
    }

    private void ejecutarOpcion(Opcion opcion) {
        if (opcion == null) return;
        switch (opcion) {
            case INSERTAR_USUARIO -> insertarUsuario();
            case BORRAR_USUARIO -> borrarUsuario();
            case MOSTRAR_USUARIOS -> mostrarUsuarios();
            case INSERTAR_LIBRO -> insertarLibro();
            case BORRAR_LIBRO -> borrarLibro();
            case MOSTRAR_LIBROS -> mostrarLibros();
            case NUEVO_PRESTAMO -> prestarLibro();
            case DEVOLVER_PRESTAMO -> devolverPrestamo();
            case MOSTRAR_PRESTAMOS -> mostrarPrestamos();
            case MOSTRAR_PRESTAMOS_USUARIO -> mostrarPrestamosUsuario();
            case SALIR -> terminar();
        }
    }

    private void insertarUsuario() {
        try {
            controlador.insertar(Consola.nuevoUsuario(false));
            System.out.println("Usuario insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarUsuario() {
        try {
            controlador.borrar(Consola.nuevoUsuario(true));
            System.out.println("Usuario borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarUsuarios() {
        List<Usuario> usuarios = controlador.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.sort(Comparator.comparing(Usuario::getNombre));
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }

    private void insertarLibro() {
        try {
            controlador.insertar(Consola.nuevoLibro(false));
            System.out.println("Libro insertado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrarLibro() {
        try {
            controlador.borrar(Consola.nuevoLibro(true));
            System.out.println("Libro borrado correctamente.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarLibros() {
        List<Libro> libros = controlador.getLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.sort(Comparator.comparing(Libro::getTitulo));
            for (Libro l : libros) {
                System.out.println(l);
            }
        }
    }

    private void prestarLibro() {
        try {
            if (controlador.getLibros().isEmpty() || controlador.getUsuarios().isEmpty()) {
                throw new IllegalStateException("ERROR: Faltan libros o usuarios en el sistema para proceder.");
            }
            Libro l = Consola.nuevoLibro(true);
            Usuario u = Consola.nuevoUsuario(true);
            controlador.prestar(l, u, Consola.leerFecha());
            System.out.println("Préstamo realizado con éxito.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void devolverPrestamo() {
        try {
            if (controlador.getPrestamos().isEmpty()) {
                throw new IllegalStateException("ERROR: No hay préstamos registrados en el sistema.");
            }
            Libro l = Consola.nuevoLibro(true);
            Usuario u = Consola.nuevoUsuario(true);

            if (controlador.devolver(l, u, Consola.leerFecha())) {
                System.out.println("Devolución realizada con éxito.");
            } else {
                System.out.println("No se ha encontrado un préstamo activo para ese libro y usuario.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarPrestamos() {
        List<Prestamo> prestamos = controlador.getPrestamos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            prestamos.sort(Comparator.comparing(Prestamo::getfInicio));
            for (Prestamo p : prestamos) {
                System.out.println(p);
            }
        }
    }

    private void mostrarPrestamosUsuario() {
        try {
            if (controlador.getUsuarios().isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            Usuario u = controlador.buscar(Consola.nuevoUsuario(true));
            if (u != null) {
                List<Prestamo> prestamos = controlador.getPrestamos(u);
                if (prestamos.isEmpty()) {
                    System.out.println("No hay préstamos para este usuario.");
                } else {
                    prestamos.sort(Comparator.comparing(Prestamo::getfInicio));
                    for (Prestamo p : prestamos) {
                        System.out.println(p);
                    }
                }
            } else {
                System.out.println("Usuario no encontrado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}