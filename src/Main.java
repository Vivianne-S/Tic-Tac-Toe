import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Skapar en Scanner för att ta emot användarinmatning
        Board board = new Board(); // Skapar ett nytt spelbräde

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();  // Läser in namn för spelare 1

        System.out.println("Do you want to play against AI? (yes/no): ");
        String againstAI = scanner.nextLine();
        boolean isAgainstAI = againstAI.equalsIgnoreCase("yes"); // Sätter isAgainstAI till true om svaret är yes, ignorerar bara yes.

        String player2Name;  // Variabel för att lagra namn på spelare 2
        if (isAgainstAI) {
            player2Name = "AI"; // Om spelaren väljer att spela mot datorn
        } else {
            System.out.print("Enter Player 2 name: ");
            player2Name = scanner.nextLine(); // Läser in namn för spelare 2
        }

        Game game = new Game(player1Name, player2Name, board, isAgainstAI); // Skapar en ny instans av spelet med spelarnas namn och brädet
        game.play(); // Startar spelet
    }
}
