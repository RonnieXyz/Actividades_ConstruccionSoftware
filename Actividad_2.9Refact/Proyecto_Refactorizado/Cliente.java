// Guarda la informacion basica de un cliente
public class Cliente {

    private final String nombre;
    private final String email;
    private final boolean socio;

    public Cliente(String nombre, String email, boolean socio) {
        this.nombre = nombre;
        this.email = email;
        this.socio = socio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean esSocio() {
        return socio;
    }
}