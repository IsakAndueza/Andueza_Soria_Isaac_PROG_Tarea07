package biblioteca.modelo;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.modelo.negocio.Libros;
import biblioteca.modelo.negocio.Prestamos;
import biblioteca.modelo.negocio.Usuarios;

import java.time.LocalDate;
import java.util.List;

public class Modelo {
    private Libros almacenLibros;
    private Usuarios registroUsuarios;
    private Prestamos gestionPrestamos;

    public void comenzar() {
        almacenLibros = new Libros();
        registroUsuarios = new Usuarios();
        gestionPrestamos = new Prestamos();
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
        if (libroReal == null) {
            throw new IllegalArgumentException("ERROR: El libro indicado no existe.");
        }
        if (usuarioReal == null) {
            throw new IllegalArgumentException("ERROR: El usuario indicado no existe.");
        }
        gestionPrestamos.prestar(libroReal, usuarioReal, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        Libro libroReal = almacenLibros.buscar(libro);
        Usuario usuarioReal = registroUsuarios.buscar(usuario);
        if (libroReal == null || usuarioReal == null) {
            return false;
        }
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

    public List<Libro> getLibros() {
        return almacenLibros.todos();
    }

    public List<Usuario> getUsuarios() {
        return registroUsuarios.todos();
    }

    public List<Prestamo> getPrestamos() {
        return gestionPrestamos.todos();
    }

    public List<Prestamo> getPrestamos(Usuario usuario) {
        return gestionPrestamos.todos(usuario);
    }
}