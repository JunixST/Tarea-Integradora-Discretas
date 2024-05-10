import java.util.*;

public class UnoGameLogic {
    private Deck deck;
    private Stack<Card> discardPile;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean isClockwise;

    public UnoGameLogic(List<String> playerNames) {
        deck = new Deck();
        discardPile = new Stack<>();
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        isClockwise = true;

        // Crear jugadores
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }

        // Distribuir cartas a los jugadores
        distributeCardsToPlayers();

        // Colocar una carta inicial en la pila de descarte
        discardPile.push(deck.drawCard());
    }

    private void distributeCardsToPlayers() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                Card card = deck.drawCard();
                player.addCardToHand(card);
            }
        }
    }

    public void playTurn(Card cardToPlay) {
        Player currentPlayer = players.get(currentPlayerIndex);

        // Verificar si la carta puede ser jugada
        if (canPlayCard(cardToPlay)) {
            // Si es una carta especial de cambio de color o +4, el jugador elige el color
            if (cardToPlay.getSpecialType().equals("Cambio de Color") || cardToPlay.getSpecialType().equals("+4")) {
                // Aquí el jugador debería elegir el siguiente color
                // Lógica para elegir el color...
            }
            // Si es una carta especial de +4, el siguiente jugador roba 4 cartas
            if (cardToPlay.getSpecialType().equals("+4")) {
                nextPlayer();
                players.get(currentPlayerIndex).drawCards(4);
            }

            // Se juega la carta y se la remueve de la mano del jugador
            currentPlayer.playCard(cardToPlay);
            discardPile.push(cardToPlay);

            // Avanzar al siguiente jugador
            nextPlayer();
        } else {
            // Si el jugador no puede jugar la carta, debe robar una carta
            Card drawnCard = deck.drawCard();
            currentPlayer.addCardToHand(drawnCard);

            // Si la carta robada puede ser jugada, el jugador la puede jugar inmediatamente
            if (canPlayCard(drawnCard)) {
                playTurn(drawnCard);
            } else {
                // Pasar al siguiente jugador
                nextPlayer();
            }
        }
    }

    private boolean canPlayCard(Card cardToPlay) {
        Card topCard = discardPile.peek();
        String topColor = topCard.getColor();
        String topSpecialType = topCard.getSpecialType();

        // Verificar si la carta coincide en color, número o tipo con la carta superior en la pila de descarte
        return cardToPlay.getColor().equals(topColor)
                || cardToPlay.getNumber() == topCard.getNumber()
                || cardToPlay.getSpecialType().equals(topSpecialType)
                || cardToPlay.getSpecialType().equals("Cambio de Color")
                || cardToPlay.getSpecialType().equals("+4");
    }

    private void nextPlayer() {
        if (isClockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    // Métodos para otras acciones del juego (por ejemplo, inversión de dirección, saltar turno, etc.)
}