package biblioteca.modelo;

import biblioteca.modelo.dominio.*;
import biblioteca.modelo.negocio.*;

import java.time.LocalDate;

public class Modelo {
    private static final int CAPACIDAD = 50;
    private Libros almacenLibros;
    private Usuarios registroUsuarios;
    private Prestamos gestionPrestamos;

    public void comenzar() {
        almacenLibros = new Libros(CAPACIDAD);
        registroUsuarios = new Usuarios(CAPACIDAD);
        gestionPrestamos = new Prestamos(CAPACIDAD);
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void alta(Libro libro) {
        almacenLibros.alta(libro);
    }

    public void alta(Usuario usuario) {
        registroUsuarios.alta(usuario);
    }

    public void prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        Libro libroReal = almacenLibros.buscar(libro);
        Usuario usuarioReal = registroUsuarios.buscar(usuario);

        if (libroReal == null) throw new IllegalArgumentException("ERROR: El libro indicado no existe.");
        if (usuarioReal == null) throw new IllegalArgumentException("ERROR: El usuario indicado no existe.");
        gestionPrestamos.prestar(libroReal, usuarioReal, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        Libro libroReal = almacenLibros.buscar(libro);
        Usuario usuarioReal = registroUsuarios.buscar(usuario);

        if (libroReal == null || usuarioReal == null) return false;

        return gestionPrestamos.devolver(libroReal, usuarioReal, fecha);
    }

    public void baja(Libro libro) {
        almacenLibros.baja(libro);
    }

    public void baja(Usuario usuario) {
        registroUsuarios.baja(usuario);
    }

    public Libro buscar(Libro libro) {
        return almacenLibros.buscar(libro);
    }

    public Usuario buscar(Usuario usuario) {
        return registroUsuarios.buscar(usuario);
    }

    public Libro[] getLibros() {
        return almacenLibros.todos();
    }

    public Usuario[] getUsuarios() {
        return registroUsuarios.todos();
    }

    public Prestamo[] getPrestamos() {
        return gestionPrestamos.historial();
    }

    public Prestamo[] getPrestamos(Usuario usuario) {
        return gestionPrestamos.prestamosUsuario(usuario);
    }
}