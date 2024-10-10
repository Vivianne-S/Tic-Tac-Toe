import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player1; // private så ingen annan klass kan ändra dessa utanför Game
    private Player player2; // om p1 väljer p2
    private Board board; // Spelbrädet där spelet äger rum
    private boolean isAgainstAI; // om p1 väljer dator

    // Konstruktor för spelare för att skapa spelare, brädet och avgöra om spelet är mot dator eller p2
    public Game(String player1Name, String player2Name, Board board, boolean isAgainstAI) {
        // Player 1 tilldelas symbol X, Player2/Dator får symbol O
        this.player1 = new Player(player1Name, 'X');
        this.player2 = new Player(player2Name, 'O');
        this.board = board;
        this.isAgainstAI = isAgainstAI; // Bestämmer om spelet spelas mot datorn
    }
    // Kör spelet med "huvud" while-loopen
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true; // Variabel för att hantera om spelaren vill spela igen

        // Starta spelet och ge alternativ att spela igen efter att spelet slutar
        while (playAgain) {
            boolean player1Turn = true; // Gör så att player1 börjar spelet
            board.resetBoard();  // Nollställer brädet
            board.printBoard();

            // Loopen som kör spelet tills någon vinner eller det blir oavgjort
            while (true) {
                if (player1Turn) {
                    System.out.println(player1.getName() + "'s turn (X):");
                    makeMove(player1);
                } else {
                    if (isAgainstAI) {
                        System.out.println("AI's turn (O):");
                        makeAIMove();
                    } else {
                        System.out.println(player2.getName() + "'s turn (O):");
                        makeMove(player2);
                    }
                }
                board.printBoard(); // Skriver ut brädet efter varje drag

                // Kolla om någon har vunnit efter varje drag
                if (board.checkWin()) {
                    if (player1Turn) {
                        System.out.println(player1.getName() + " wins!");
                    } else if (isAgainstAI) {
                        System.out.println("AI wins!");
                    } else {
                        System.out.println(player2.getName() + " wins!");
                    }
                    break;
                }
                // Kolla om brädet är fullt (oavgjort)
                if (board.isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                player1Turn = !player1Turn;  // Växlar turen mellan spelarna
            }

            // Efter att spelet är slut, fråga om spelarna vill spela igen
            System.out.println("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for playing! Goodbye.");
    }

    // Hanterar spelarens drag p1-p2 med hjälp av metoden giltig inmatning från Board
    private void makeMove(Player player) {
        int row = board.getValidInput("Enter row (1, 2, 3): ");
        int col = board.getValidInput("Enter column (1, 2, 3): ");
        // Om platsen redan är upptagen och INTE kan placeras returnerar false och man får göra ett nytt drag
        while (!board.placeMove(row, col, player.getSymbol())) {
            System.out.println("This cell is already taken. Try again.");
            row = board.getValidInput("Enter row (1, 2, 3): ");
            col = board.getValidInput("Enter column (1, 2, 3): ");
        }
    }

    // AI gör ett slumpmässigt drag
    private void makeAIMove() {
        Random rand = new Random();
        int row, col;
        // Loopen fortsätter så länge som metoden placeMove() från Board klassen returnerar false, det vill säga så länge det inte finns en tom plats.
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!board.placeMove(row, col, player2.getSymbol())); // Datorn använder 'O' som Player2 då den är P2 och fortsätter tills den hittar en tom plats.
    }
}
