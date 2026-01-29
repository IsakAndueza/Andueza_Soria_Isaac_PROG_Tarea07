package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Usuario;

import java.util.Arrays;

public class Usuarios {
    private int capacidad, tamano;
    private Usuario[] coleccionUsuarios;

    public Usuarios(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("ERROR: Capacidad positiva.");
        this.capacidad = capacidad;
        this.coleccionUsuarios = new Usuario[capacidad];
        this.tamano = 0;
    }

    public void alta(Usuario usuario) {
        if (usuario == null) throw new NullPointerException("ERROR: Usuario nulo.");
        if (tamano >= capacidad) throw new IllegalStateException("ERROR: No hay espacio.");
        if (buscar(usuario) != null) throw new IllegalStateException("ERROR: Ya existe el usuario.");
        coleccionUsuarios[tamano++] = new Usuario(usuario);
    }

    public void baja(Usuario usuario) {
        if (usuario == null) return;
        for (int i = 0; i < tamano; i++) {
            if (coleccionUsuarios[i].equals(usuario)) {
                for (int j = i; j < tamano - 1; j++) coleccionUsuarios[j] = coleccionUsuarios[j + 1];
                coleccionUsuarios[--tamano] = null;
                return;
            }
        }
    }

    public Usuario buscar(Usuario usuario) {
        if (usuario == null) return null;
        for (int i = 0; i < tamano; i++)
            if (coleccionUsuarios[i].equals(usuario)) return new Usuario(coleccionUsuarios[i]);
        return null;
    }

    public Usuario[] todos() {
        return Arrays.copyOf(coleccionUsuarios, tamano);
    }
}