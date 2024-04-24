import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String menu = """
                             Conversor De Monedas
                
                +---------------------------------------------------+
                | Opción |            Conversión                    |
                +---------------------------------------------------+
                |   1   | USD (Dolares) -> EUR (EUROS)              |
                |   2   | EUR (EUROS) -> USD (Dolares)              |
                |   3   | USD (Dolares) -> CLP (Pesos Chilenos)     |
                |   4   | CLP (Pesos Chilenos) -> USD (Dolares)     |
                |   5   | USD (Dolares) -> ARS (Pesos Argentinos)   |
                |   6   | ARS (Pesos Argentinos) -> USD (Dolares)   |
                |   7   | USD (Dolares) -> BRL (Reales Brasileños)  |
                |   8   | BRL (Reales Brasileños) -> USD (Dolares)  |
                |   9   | Salir                                     |
                +---------------------------------------------------+
                """;


        int opcion;
        do {
            System.out.println(menu);
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1, 2, 3, 4, 5, 6, 7, 8:
                    convertirMoneda(opcion, scanner);
                    break;
                case 9:
                    System.out.println("Convesor Finalizado");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
            }
        } while (opcion != 9);
        scanner.close();
    }

    private static void convertirMoneda(int opcion, Scanner scanner) {
        String[] monedas = {"EUR", "USD", "CLP", "ARS", "BRL"};
        String base = "USD";
        String target = "";
        double amount;

        switch (opcion) {
            case 1, 3, 5, 7:
                target = monedas[(opcion - 1) / 2];
                break;
            case 2, 4, 6, 8:
                target = "USD";
                base = monedas[(opcion - 2) / 2];
                break;
        }

        System.out.print("Ingrese el monto a convertir: ");
        amount = scanner.nextDouble();

        try {
            Conversion conversion = new Conversion(base, target, amount);
            double resultado = conversion.convertidor();
            System.out.println("+----------------------------------------------------------+");
            System.out.println("Resultado de la conversión: " + resultado + " " + target);
            System.out.println("+----------------------------------------------------------+");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }

        System.out.println("________________________________________________________________");
    }
}