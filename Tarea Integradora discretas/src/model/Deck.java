import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        // Agrega todas las cartas al mazo
        for (String color : Card.COLORS) {
            // Cartas numéricas
            for (int number = 0; number <= 9; number++) {
                cards.add(new Card(color, number));
            }
            // Cartas especiales (2 de cada tipo)
            cards.add(new Card(color, "Saltar"));
            cards.add(new Card(color, "Reversa"));
            cards.add(new Card(color, "Comer 2"));
        }
        // Cartas de cambio de color y +4 (4 de cada tipo)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card("Cambio de Color", ""));
            cards.add(new Card("+4", ""));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("El mazo está vacío");
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
}