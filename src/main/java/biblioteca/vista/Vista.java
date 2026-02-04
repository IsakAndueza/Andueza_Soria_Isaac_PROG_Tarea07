package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Usuario;

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
            case NUEVO_PRESTAMO -> realizarPrestamo();
            case DEVOLVER_PRESTAMO -> devolverPrestamo();
            case MOSTRAR_PRESTAMOS -> mostrarPrestamos();
            case MOSTRAR_PRESTAMOS_USUARIO -> mostrarPrestamosUsuario();
            case SALIR -> terminar();
        }
    }

    private void insertarUsuario() {
        controlador.insertar(Consola.nuevoUsuario(false));
        System.out.println("Usuario insertado con éxito.");
    }

    private void borrarUsuario() {
        if (controlador.getUsuarios().isEmpty()) {
            System.out.println("No hay usuarios registrados para borrar.");
            return;
        }
        controlador.borrar(Consola.nuevoUsuario(true));
        System.out.println("Operación de borrado finalizada.");
    }

    private void mostrarUsuarios() {
        mostrarLista(controlador.getUsuarios());
    }

    private void insertarLibro() {
        controlador.insertar(Consola.nuevoLibro(false));
        System.out.println("Libro insertado con éxito.");
    }

    private void borrarLibro() {
        if (controlador.getLibros().isEmpty()) {
            System.out.println("No hay libros registrados para borrar.");
            return;
        }
        controlador.borrar(Consola.nuevoLibro(true));
        System.out.println("Operación de borrado finalizada.");
    }

    private void mostrarLibros() {
        mostrarLista(controlador.getLibros());
    }

    private void realizarPrestamo() {
        if (controlador.getLibros().isEmpty()) {
            throw new IllegalStateException("ERROR: No se pueden realizar préstamos porque no hay libros en la biblioteca.");
        }
        if (controlador.getUsuarios().isEmpty()) {
            throw new IllegalStateException("ERROR: No se pueden realizar préstamos porque no hay usuarios registrados.");
        }

        Libro libro = controlador.buscar(Consola.nuevoLibro(true));
        if (libro == null) {
            throw new IllegalArgumentException("ERROR: El libro indicado no existe.");
        }
        if (libro.getUnidades() <= 0) {
            throw new IllegalStateException("ERROR: No quedan unidades disponibles de este libro.");
        }

        Usuario usuario = controlador.buscar(Consola.nuevoUsuario(true));
        if (usuario == null) {
            throw new IllegalArgumentException("ERROR: El usuario indicado no existe.");
        }

        controlador.prestar(libro, usuario, Consola.leerFecha());
        System.out.println("Préstamo registrado correctamente.");
    }

    private void devolverPrestamo() {
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
    }

    private void mostrarPrestamos() {
        mostrarLista(controlador.getPrestamos());
    }

    private void mostrarPrestamosUsuario() {
        if (controlador.getUsuarios().isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        Usuario u = controlador.buscar(Consola.nuevoUsuario(true));
        if (u != null) {
            mostrarLista(controlador.getPrestamos(u));
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private void mostrarLista(List<?> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("No hay datos registrados.");
        } else {
            for (Object o : lista) {
                System.out.println(o);
                System.out.println("-------------------------------------------");
            }
        }
    }
}