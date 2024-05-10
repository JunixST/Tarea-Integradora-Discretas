import java.util.*;

public class Card {
    private String color;
    private int number;
    private String specialType; // Tipo especial de carta: Cambio de Color, +4, Saltar, Reversa, etc.

    public Card(String color, int number) {
        this.color = color;
        this.number = number;
        this.specialType = null;
    }

    public Card(String color, String specialType) {
        this.color = color;
        this.specialType = specialType;
    }

    // Getters y setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }
}