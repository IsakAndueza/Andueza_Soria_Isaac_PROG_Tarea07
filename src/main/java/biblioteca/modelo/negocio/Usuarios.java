package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    private List<Usuario> coleccionUsuarios;

    public Usuarios() {
        coleccionUsuarios = new ArrayList<>();
    }

    public void alta(Usuario usuario) {
        if (usuario == null) {
            throw new NullPointerException("ERROR: No se puede dar de alta un usuario nulo.");
        }
        if (coleccionUsuarios.contains(usuario)) {
            throw new IllegalStateException("ERROR: Ya existe un usuario con ese ID.");
        }
        coleccionUsuarios.add(new Usuario(usuario));
    }

    public void baja(Usuario usuario) {
        if (usuario == null) {
            throw new NullPointerException("ERROR: No se puede dar de baja un usuario nulo.");
        }
        if (!coleccionUsuarios.contains(usuario)) {
            throw new IllegalStateException("ERROR: El usuario no existe.");
        }
        coleccionUsuarios.remove(usuario);
    }

    public Usuario buscar(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        int indice = coleccionUsuarios.indexOf(usuario);
        if (indice != -1) {
            return coleccionUsuarios.get(indice);
        }
        return null;
    }

    public List<Usuario> todos() {
        List<Usuario> copia = new ArrayList<>();
        for (Usuario u : coleccionUsuarios) {
            copia.add(new Usuario(u));
        }
        return copia;
    }
}