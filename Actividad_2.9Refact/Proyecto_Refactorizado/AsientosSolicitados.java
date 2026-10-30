import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TECNICA UTILIZADA: Introduce Parameter Object
/* REFACTOR: Introduce Parameter Object
/**
 * Los datos de los asientos se agrupan en una sola clase
 * Ya no se pasan varios arreglos por separado
 * La informacion viaja en un solo objeto
 */

public class AsientosSolicitados {

    private final List<String> codigos;

    public AsientosSolicitados(char[] filas, int[] numeros) {

        if (filas == null || numeros == null) {
            throw new IllegalArgumentException(
                    "Las filas y los numeros de asiento son obligatorios");
        }

        if (filas.length == 0 || filas.length != numeros.length) {
            throw new IllegalArgumentException(
                    "Debe indicar la misma cantidad de filas y numeros de asiento");
        }

        List<String> temporal = new ArrayList<>();

        for (int i = 0; i < filas.length; i++) {
            temporal.add("" + filas[i] + numeros[i]);
        }

        this.codigos = Collections.unmodifiableList(temporal);
    }

    public List<String> getCodigos() {
        return codigos;
    }
}