package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.*;

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
                int ordinal = Consola.elegirOpcion();
                opcion = Opcion.getOpcionSegunOrdinal(ordinal);
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
        try {
            switch (opcion) {
                case INSERTAR_USUARIO -> {
                    controlador.insertar(Consola.nuevoUsuario(false));
                    System.out.println("Usuario registrado correctamente.");
                }
                case BORRAR_USUARIO -> controlador.borrar(Consola.nuevoUsuario(true));
                case MOSTRAR_USUARIOS -> mostrarArray(controlador.getUsuarios());
                case INSERTAR_LIBRO -> {
                    controlador.insertar(Consola.nuevoLibro(false));
                    System.out.println("Libro registrado correctamente.");
                }
                case BORRAR_LIBRO -> controlador.borrar(Consola.nuevoLibro(true));
                case MOSTRAR_LIBROS -> mostrarArray(controlador.getLibros());
                case NUEVO_PRESTAMO -> realizarPrestamo();
                case DEVOLVER_PRESTAMO -> devolverPrestamo();
                case MOSTRAR_PRESTAMOS -> mostrarArray(controlador.getPrestamos());
                case MOSTRAR_PRESTAMOS_USUARIO -> mostrarPrestamosUsuario();
                case SALIR -> {
                    if (controlador != null) controlador.terminar();
                }
            }
        } catch (Exception e) {
            System.out.println("\n>>> ERROR: " + e.getMessage());
        }
    }

    private void mostrarArray(Object[] array) {
        if (array == null || array.length == 0) {
            System.out.println("No hay datos que mostrar.");
            return;
        }
        for (Object o : array) {
            if (o != null) {
                System.out.println(o);
                System.out.println("-------------------------------------------");
            }
        }
    }

    private void realizarPrestamo() {
        Usuario uDummy = Consola.nuevoUsuario(true);
        Libro lDummy = Consola.nuevoLibro(true);

        controlador.prestar(lDummy, uDummy, Consola.leerFecha());
        System.out.println("Préstamo registrado con éxito.");
    }

    private void devolverPrestamo() {
        if (controlador.getPrestamos().length == 0) {
            throw new IllegalStateException("No hay préstamos realizados en el sistema.");
        }
        Usuario uDummy = Consola.nuevoUsuario(true);
        Libro lDummy = Consola.nuevoLibro(true);

        if (controlador.devolver(lDummy, uDummy, Consola.leerFecha())) {
            System.out.println("Devolución realizada correctamente.");
        } else {
            System.out.println("No se ha encontrado un préstamo activo para ese usuario y libro.");
        }
    }

    private void mostrarPrestamosUsuario() {
        Usuario u = controlador.buscar(Consola.nuevoUsuario(true));
        if (u == null) throw new IllegalArgumentException("El usuario no existe.");

        Prestamo[] prestamos = controlador.getPrestamos(u);
        if (prestamos.length == 0) {
            System.out.println("El usuario no tiene préstamos registrados.");
        } else {
            mostrarArray(prestamos);
        }
    }
}