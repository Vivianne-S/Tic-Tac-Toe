public class Player {
    private String name; // spelarens/nas namn
    private char symbol; // och symboler

    // Konstruktor för att skapa en ny spelare
    public Player(String name, char symbol) {
        this.name = name; // sätter spelarens namn så Java vet att det är DET HÄR namnet vi menar och inte player1name som används för tur på drag
        this.symbol = symbol; // samma med symbol
    }
    // Getter för att kunna återanvända namnet i andra klasser/metoder
    public String getName() {
        return name;
    }
    // Getter för att hämta spelarens symbol X eller O
    public char getSymbol() {
        return symbol;
    }

}
