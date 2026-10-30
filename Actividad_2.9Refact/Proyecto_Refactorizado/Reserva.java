import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reserva {

    private final int id;
    private final Cliente cliente;
    private final Funcion funcion;
    private final List<String> asientos;
    private final String canal;
    private final double precio;
    private boolean cancelada;

    public Reserva(int id, Cliente cliente, Funcion funcion, List<String> asientos, String canal, double precio) {

        this.id = id;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asientos = new ArrayList<>(asientos);
        this.canal = canal;
        this.precio = precio;
        this.cancelada = false;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public List<String> getAsientos() {
        return Collections.unmodifiableList(asientos);
    }

    public String getCanal() {
        return canal;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean estaCancelada() {
        return cancelada;
    }

    // TECNICA UTILIZADA: Remove Setting Method
    // REFACTOR: Remove Setting Method
    /**
     * Se elimina el metodo setCancelada
     * El estado cambia usando cancelar()
     * Esto evita cambios incorrectos
     */
    
    public void cancelar() {

        if (cancelada) {
            throw new IllegalStateException(
                    "La reserva " + id + " ya esta cancelada");
        }

        for (String asiento : asientos) {
            funcion.liberarAsiento(asiento);
        }

        cancelada = true;
    }
}