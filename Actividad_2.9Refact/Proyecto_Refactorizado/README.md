# Sistema de reservas de cine — versión refactorizada

## Descripción
Proyecto Java de consola para crear, consultar y cancelar reservas de asientos para una función de cine. La refactorización conserva el comportamiento principal, corrige la ocupación parcial de asientos y reemplaza los códigos numéricos de error por excepciones descriptivas.

## Requisitos
- JDK 8 o superior.
- Terminal o IDE compatible con Java.

## Ejecutar
Desde la carpeta que contiene los archivos `.java`:

```bash
javac *.java
java Main
```

## Salida esperada aproximada

```text
Reserva Ana: 1
Reserva Luis: error controlado - No se pudo crear la reserva: el asiento F6 ya está ocupado.
Ocupados: [F5, F6]
Precio Ana (socio): 20.4
Cancelar 1: OK
Cancelar 99: error controlado - No existe una reserva con el identificador 99.
```

El texto exacto de los mensajes de error depende de las excepciones lanzadas, pero se deben cumplir estas condiciones:
- Solo F5 y F6 quedan ocupados por la reserva de Ana.
- La solicitud de Luis falla porque F6 ya está ocupado; F7 no queda bloqueado.
- El precio de Ana sigue siendo 20.4.
- Cancelar la reserva 99 produce un error controlado y descriptivo, no un código numérico.

## Técnicas aplicadas

1. **Rename method:** `hacer(...)` cambia a `crearReserva(...)`, nombre que describe la acción.
2. **Remove parameter:** se elimina `imprimir`, ya que no se utilizaba.
3. **Add parameter:** el canal de reserva se conserva y se almacena en `Reserva`; antes se recibía pero se ignoraba.
4. **Preserve whole object:** `crearReserva` recibe un `Cliente` en lugar de tres datos sueltos.
5. **Introduce parameter object:** `AsientosSolicitados` agrupa los códigos de asientos y valida los arreglos al construir el objeto.
6. **Replace parameter with method:** el precio se calcula dentro del flujo de creación usando los datos de la función y la cantidad de asientos, sin recibir un precio calculado externamente.
7. **Replace parameter with explicit method:** se reemplaza el parámetro textual `tipo` por `calcularPrecioGeneral` y `calcularPrecioSocio`.
8. **Parameterize method:** `aplicarDescuento(precio, porcentaje)` sustituye métodos duplicados de descuento.
9. **Separate query from modifier:** `estaAsientoOcupado` consulta sin modificar; `ocuparAsiento` cambia el estado.
10. **Hide method:** `obtenerReserva` es privado porque solo es un auxiliar interno; la consulta pública se realiza con `buscarReserva`. Además, `Funcion` mantiene privada la lista de asientos y expone una vista no modificable.
11. **Remove setting method:** se elimina `Funcion.setPrecio`; el precio se establece en el constructor y es inmutable.
12. **Remove setting method:** se elimina `Reserva.setCancelada`; el estado cambia mediante `Reserva.cancelar()`.
13. **Encapsulate downcast:** se elimina el mapa `Map<Integer, Object>` y se usa `Map<Integer, Reserva>`, además de concentrar la búsqueda en `obtenerReserva`.
14. **Replace error code with exception:** la cancelación y la búsqueda reportan reservas inexistentes mediante `ReservaNoEncontradaException`, en lugar de retornar `-1`.
15. **Replace exception with test:** antes de ocupar asientos, el sistema comprueba que todos estén libres. Así se evita usar una excepción como mecanismo normal de control y se corrige el bloqueo parcial.

## Clases
- `Main`: demuestra el flujo de reserva, consulta de ocupados y cancelación.
- `SistemaReservas`: coordina las reservas y calcula los precios.
- `Cliente`: representa al cliente.
- `Funcion`: representa la función y controla los asientos ocupados.
- `Reserva`: almacena los datos de una reserva y permite cancelarla.
- `AsientosSolicitados`: objeto de parámetro para los asientos.
- `AsientoOcupadoException`: error controlado cuando un asiento ya está ocupado.
- `ReservaNoEncontradaException`: error controlado cuando no existe la reserva solicitada.

## Diagramas de clases
La actividad solicita incluir dos diagramas en digital: uno del proyecto original y otro del refactorizado. Deben agregarse al repositorio junto a estos archivos. El diagrama refactorizado debe mostrar las relaciones entre `SistemaReservas`, `Reserva`, `Cliente`, `Funcion`, `AsientosSolicitados` y las dos excepciones.

## Verificación
Se recomienda ejecutar `javac *.java` y `java Main`, y conservar la salida de consola como evidencia. Comparar con el comportamiento original teniendo en cuenta la corrección solicitada para el bug de F7.
