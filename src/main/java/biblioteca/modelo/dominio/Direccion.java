package biblioteca.modelo.dominio;

public class Direccion {
    private static final String CP_PATTERN = "\\d{5}";
    private String via, numero, cp, localidad;

    public Direccion(String via, String numero, String cp, String localidad) {
        setVia(via);
        setNumero(numero);
        setCp(cp);
        setLocalidad(localidad);
    }

    public void setCp(String cp) {
        if (cp == null || !cp.matches(CP_PATTERN))
            throw new IllegalArgumentException("ERROR: El código postal debe tener 5 dígitos.");
        this.cp = cp;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getVia() {
        return via;
    }

    public String getNumero() {
        return numero;
    }

    public String getCp() {
        return cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    @Override
    public String toString() {
        return String.format("%s, %s. CP: %s, %s", via, numero, cp, localidad);
    }
}
