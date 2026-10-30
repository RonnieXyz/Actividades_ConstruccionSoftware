// Excepcion usada cuando un asiento ya fue reservado
public class AsientoOcupadoException extends RuntimeException {

    public AsientoOcupadoException(String mensaje) {
        super(mensaje);
    }

}