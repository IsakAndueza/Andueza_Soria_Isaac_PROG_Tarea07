package biblioteca.controlador;

import biblioteca.modelo.Modelo;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.vista.Vista;

import java.time.LocalDate;
import java.util.List;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null || vista == null) {
            throw new NullPointerException("ERROR: El modelo y la vista no pueden ser nulos.");
        }
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
        System.out.println("Termina Controlador");
    }

    public void insertar(Usuario usuario) {
        modelo.alta(usuario);
    }

    public void insertar(Libro libro) {
        modelo.alta(libro);
    }

    public void borrar(Usuario usuario) {
        modelo.baja(usuario);
    }

    public void borrar(Libro libro) {
        modelo.baja(libro);
    }

    public void prestar(Libro libro, Usuario usuario, LocalDate fecha) {
        modelo.prestar(libro, usuario, fecha);
    }

    public boolean devolver(Libro libro, Usuario usuario, LocalDate fecha) {
        return modelo.devolver(libro, usuario, fecha);
    }

    public Usuario buscar(Usuario usuario) {
        return modelo.buscar(usuario);
    }

    public Libro buscar(Libro libro) {
        return modelo.buscar(libro);
    }

    public List<Usuario> getUsuarios() {
        return modelo.getUsuarios();
    }

    public List<Libro> getLibros() {
        return modelo.getLibros();
    }

    public List<Prestamo> getPrestamos() {
        return modelo.getPrestamos();
    }

    public List<Prestamo> getPrestamos(Usuario usuario) {
        return modelo.getPrestamos(usuario);
    }
}