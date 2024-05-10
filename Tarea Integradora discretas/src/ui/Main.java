import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido al juego Uno");
            System.out.println("1. Jugar partida");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (option) {
                case 1:
                    startGame(scanner);
                    break;
                case 2:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    public static void startGame(Scanner scanner) {
        // Solicitar el número de jugadores y los nombres de los jugadores
        System.out.print("Ingrese el número de jugadores (2-4): ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        // Validar el número de jugadores
        if (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Número de jugadores no válido. Deben ser entre 2 y 4 jugadores.");
            return;
        }

        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Nombre del jugador " + (i + 1) + ": ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
        }

        // Iniciar el juego Uno
        UnoGame unoGame = new UnoGame(playerNames);

        // Bucle del juego
        while (true) {
            Player currentPlayer = unoGame.getCurrentPlayer();
            List<Card> availableCards = currentPlayer.getAvailableCards(unoGame.getTopCard());

            // Mostrar las cartas disponibles para jugar
            System.out.println("Es el turno de " + currentPlayer.getName());
            System.out.println("Cartas disponibles para jugar:");
            for (int i = 0; i < availableCards.size(); i++) {
                System.out.println((i + 1) + ". " + availableCards.get(i));
            }

            // Si el jugador no tiene cartas disponibles, debe robar una carta
            if (availableCards.isEmpty()) {
                System.out.println("No tienes cartas disponibles para jugar. Roba una carta del mazo.");
                currentPlayer.drawCards(1);
                unoGame.nextPlayer();
                continue;
            }

            // Solicitar al jugador que elija una carta para jugar
            System.out.print("Selecciona una carta para jugar (ingresa el número de la carta): ");
            int cardIndex = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            // Validar la selección del jugador
            if (cardIndex < 1 || cardIndex > availableCards.size()) {
                System.out.println("Selección no válida. Por favor, selecciona una carta válida.");
                continue;
            }

            // Jugar la carta seleccionada
            unoGame.playTurn(availableCards.get(cardIndex - 1));

            // Verificar si el jugador ganó
            if (currentPlayer.getHand().isEmpty()) {
                System.out.println(currentPlayer.getName() + " ha ganado el juego!");
                break;
            }

            // Cambiar al siguiente jugador
            unoGame.nextPlayer();
        }
    }
}