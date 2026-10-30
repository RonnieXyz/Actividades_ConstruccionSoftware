import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaReservas {

    private final Map<Integer, Reserva> reservas = new HashMap<>();
    private int contador = 1;

    // TECNICA UTILIZADA: Rename Method
    // REFACTOR: Rename Method
    /**
     * El metodo hacer() cambia a crearReserva()
     * El nuevo nombre describe mejor su funcion
     * Es mas facil entender lo que realiza
     */

    // TECNICA UTILIZADA: Remove Parameter
    // REFACTOR: Remove Parameter
    /**
     * Se elimina el parametro imprimir
     * No participaba en la operacion
     * Se simplifica la llamada al metodo
     */

    // TECNICA UTILIZADA: Preserve Whole Object
    // REFACTOR: Preserve Whole Object
    /**
     * Se recibe un objeto Cliente completo
     * Ya no se envian sus datos por separado
     * La informacion viaja en una sola unidad
     */

    // TECNICA UTILIZADA: Introduce Parameter Object
    // REFACTOR: Introduce Parameter Object
    /**
     * Los datos de los asientos se agrupan en una clase
     * Ya no se usan arreglos paralelos
     * El codigo queda mas organizado
     */

    // TECNICA UTILIZADA: Add Parameter
    // REFACTOR: Add Parameter
    /**
     * Se agrega el parametro canal
     * El dato ahora se almacena en la reserva
     * Ya no se pierde durante el proceso
     */

    // TECNICA UTILIZADA: Replace Exception with Test
    // REFACTOR: Replace Exception with Test
    /**
     * Se revisan todos los asientos antes de ocuparlos
     * Primero se valida la disponibilidad
     * Se evitan cambios parciales
     */
    
    public int crearReserva(Cliente cliente, Funcion funcion, AsientosSolicitados solicitados, String canal) {

        if (cliente == null || funcion == null || solicitados == null) {
            throw new IllegalArgumentException(
                    "El cliente la funcion y los asientos son obligatorios");
        }

        List<String> asientos = solicitados.getCodigos();

        for (String asiento : asientos) {
            if (funcion.estaAsientoOcupado(asiento)) {
                throw new AsientoOcupadoException(
                        "No se pudo crear la reserva el asiento "
                                + asiento + " ya esta ocupado");
            }
        }

        for (String asiento : asientos) {
            funcion.ocuparAsiento(asiento);
        }

        double precio = cliente.esSocio()
                ? calcularPrecioSocio(funcion, asientos.size())
                : calcularPrecioGeneral(funcion, asientos.size());

        Reserva reserva = new Reserva(contador, cliente, funcion, asientos, canal, precio);

        reservas.put(contador, reserva);

        return contador++;
        
    }

    // TECNICA UTILIZADA: Replace Parameter with Method
    // REFACTOR: Replace Parameter with Method
    /**
     * El precio se obtiene desde Funcion
     * Ya no se envia como parametro
     * Se evita duplicar informacion
     */


    // TECNICA UTILIZADA: Replace Parameter with Explicit Method
    // REFACTOR: Replace Parameter with Explicit Method
    /**
     * Se eliminan los tipos enviados como texto
     * Se crean metodos para cada caso
     * El codigo queda mas claro
     */

    public double calcularPrecioGeneral(Funcion funcion, int cantidadAsientos) {

        return funcion.getPrecio() * cantidadAsientos;

    }


    public double calcularPrecioSocio(Funcion funcion, int cantidadAsientos) {

        return aplicarDescuento(calcularPrecioGeneral(funcion, cantidadAsientos),0.15);

    }

    // TECNICA UTILIZADA: Parameterize Method
    // REFACTOR: Parameterize Method
    /**
     * Un solo metodo calcula cualquier descuento
     * Ya no existen varias versiones parecidas
     * Solo cambia el porcentaje recibido
     */

    private double aplicarDescuento(double precio,double porcentaje) {

        return precio - precio * porcentaje;

    }

    // TECNICA UTILIZADA: Encapsulate Downcast
    // REFACTOR: Encapsulate Downcast
    /**
     * La busqueda devuelve directamente una Reserva
     * No es necesario realizar conversiones
     * El acceso queda centralizado
     */


    // TECNICA UTILIZADA: Hide Method
    // REFACTOR: Hide Method
    /**
     * obtenerReserva() se usa solo dentro de la clase
     * Se marca como private
     * buscarReserva() queda como acceso publico
     */

    private Reserva obtenerReserva(int id) {

        Reserva reserva = reservas.get(id);

        if (reserva == null) {
            throw new ReservaNoEncontradaException(
                    "No existe una reserva con codigo: " + id);
        }

        return reserva;
    }

    // Busca una reserva usando su identificador
    public Reserva buscarReserva(int id) {
        return obtenerReserva(id);
    }

    // TECNICA UTILIZADA: Replace Error Code with Exception
    // REFACTOR: Replace Error Code with Exception
    /**
     * Ya no se devuelven codigos numericos
     * Los errores se indican con excepciones
     * El resultado es mas facil de entender
     */

    public void cancelarReserva(int id) {

        Reserva reserva = obtenerReserva(id);
        reserva.cancelar();
    }
}