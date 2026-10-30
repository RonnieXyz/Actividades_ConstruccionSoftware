// Excepcion usada cuando no existe una reserva
public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}