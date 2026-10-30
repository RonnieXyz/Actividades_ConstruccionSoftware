import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Funcion {

    private final String titulo;
    private final int sala;
    private final String hora;
    private final double precio;
    private final List<String> ocupados = new ArrayList<>();

    public Funcion(String titulo, int sala, String hora, double precio) {
        this.titulo = titulo;
        this.sala = sala;
        this.hora = hora;

        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getSala() {
        return sala;
    }

    public String getHora() {
        return hora;
    }

    public double getPrecio() {
        return precio;
    }

    // TECNICA UTILIZADA: Separate Query from Modifier
    // REFACTOR: Separate Query from Modifier
    /**
     * Se separa la consulta de la modificacion
     * este metodo solo verifica si el asiento existe
     * no realiza cambios en la coleccion
     */

    public boolean estaAsientoOcupado(String codigo) {
        return ocupados.contains(codigo);
    }

    // Ocupa un asiento despues de validar disponibilidad
    public void ocuparAsiento(String codigo) {
        if (estaAsientoOcupado(codigo)) {
            throw new AsientoOcupadoException("El asiento " + codigo + " ya esta ocupado");
        }

        ocupados.add(codigo);
    }

    // Libera un asiento de la funcion
    public void liberarAsiento(String codigo) {
        ocupados.remove(codigo);
    }

    // TECNICA UTILIZADA: Encapsulate Collection
    // REFACTOR: Encapsulate Collection
    /**
     * La coleccion queda protegida dentro de la clase
     * solo se devuelve una vista de lectura
     * se evita modificar los datos desde afuera
     */

    public List<String> getAsientosOcupados() {

        return Collections.unmodifiableList(ocupados);

    }
}