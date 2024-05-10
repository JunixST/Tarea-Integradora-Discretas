import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void playCard(Card card) {
        hand.remove(card);
    }

    public void drawCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public List<Card> getAvailableCards(Card topCard) {
        List<Card> availableCards = new ArrayList<>();
        for (Card card : hand) {
            if (canPlayCard(card, topCard)) {
                availableCards.add(card);
            }
        }
        return availableCards;
    }

    private boolean canPlayCard(Card card, Card topCard) {
        // Lógica para determinar si una carta se puede jugar
        return card.getColor().equals(topCard.getColor())
                || card.getNumber() == topCard.getNumber()
                || card.getSpecialType().equals(topCard.getSpecialType())
                || card.getSpecialType().equals("Cambio de Color")
                || card.getSpecialType().equals("+4");
    }
}