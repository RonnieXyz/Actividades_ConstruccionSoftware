public class Main {

    public static void main(String[] args) {

        // Crear sistema de reservas
        SistemaReservas sistema = new SistemaReservas();

        // Crear funcion disponible
        Funcion funcion = new Funcion("Interestelar", 3, "20:30", 12.0);

        // Crear reserva para Ana
        int idAna = sistema.crearReserva( new Cliente("Ana", "ana@mail.com", true),
                funcion,
                new AsientosSolicitados(
                        new char[]{'F', 'F'},
                        new int[]{5, 6}
                ),
                "App"
        );

        System.out.println("Reserva Ana: " + idAna);

        try {

            // Intentar reservar un asiento ocupado
            sistema.crearReserva( new Cliente("Luis", "luis@mail.com", false),
                    funcion,
                    new AsientosSolicitados(
                            new char[]{'F', 'F'},
                            new int[]{7, 6}
                    ),
                    "Taquilla"
            );

        } catch (AsientoOcupadoException e) {

            System.out.println( "Reserva Luis: error: " + e.getMessage());
        }

        // Mostrar asientos ocupados
        System.out.println( "Ocupados: " + funcion.getAsientosOcupados());

        // Buscar reserva creada
        Reserva ana = sistema.buscarReserva(idAna);

        System.out.println("Precio Ana (socio): " + ana.getPrecio());

        try {

            // Cancelar reserva existente
            sistema.cancelarReserva(idAna);

            System.out.println("Cancelar 1: Si");

        } catch (IllegalStateException e) {

            System.out.println( "Cancelar 1: error:  "+ e.getMessage());
        }

        try {

            // Intentar cancelar una reserva inexistente
            sistema.cancelarReserva(99);

            System.out.println("Cancelar 99: OK");

        } catch (ReservaNoEncontradaException e) {

            System.out.println("Cancelar 99: error: " + e.getMessage());
        }
    }
}
