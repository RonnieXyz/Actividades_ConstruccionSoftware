# Proyecto Refactorizado

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

