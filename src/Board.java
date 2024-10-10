import java.util.Scanner;

public class Board {
    private char[][] board; // 2D-array för att representera spelbrädet

    // Konstruktor för att skapa brädet med tomma platser när spelet startar
    public Board() {
        board = new char[3][3];
        resetBoard();
    }

    // Skriver ut spelbrädet via Array med två for loopar som skriver ut alla detaljer i brädet
    public void printBoard() {
        System.out.println("Current board:");
        // Skriv ut kolumnnummer
        System.out.println("   1   2   3"); // Kolumnetiketter
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " "); // Radetikett
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j]); // Ger ett mellanslag till - i varje plats
                if (j < 2) {
                    System.out.print(" |"); // Lägger till vertikala linjer mellan kolumnerna
                }
            }
            System.out.println(); // Ny rad efter varje rad på brädet
            if (i < 2) {
                System.out.println("  ---+---+---"); // Lägger till horisontella linjer mellan raderna
            }
        }
    }

    // Nollställer brädet och fyller alla tomma platser i varje rad/kolum med - så att alla platser är tomma när spelet startar
    public void resetBoard() {
        // Går igenom varje rad (i) och kolumn (j) i brädet
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Sätter varje plats till '-' för att representera en tom plats
                board[i][j] = '-';
            }
        }
    }

    // Placerar spelarens symbol på den angivna platsen om den är ledig fortsätter spelet med return false, om den är upptagen blir det false och ber om nytt försök
    public boolean placeMove(int row, int col, char symbol) {
        // Kontrollerar om den valda platsen är tom ('-')
        if (board[row][col] == '-') {
            // Om platsen är tom, placera spelarens symbol på den platsen
            board[row][col] = symbol;
            return true;  // Flytten lyckades om platsen är ledig
        }
        return false; // Flytten misslyckades om platsen redan är upptagen
    }

    // Kontrollerar om någon har vunnit
    public boolean checkWin() {
        // Kolla rader, kolumner och diagonaler som loopas igenom för tre lika symboler
        for (int i = 0; i < 3; i++) {
            // Vinst om tre samma symbol i rad
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return true;  // Vinnare hittad
            }
            // Vinst om tre samma symboler i kolum
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '-') {
                return true;
            }
        }
        // Vinst vid tre sammma symboler diagonalt vänster upp till höger ner
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return true;
        }
        // Vinst vid tre samma symboler diagonalt höger upp vänster ner
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != '-') {
            return true;
        }
        return false;  // Ingen vinnare
    }

    // Kontrollerar om brädet är fullt med boolean för att avgöra om spelet är oavgjort utan tre i rad
    public boolean isBoardFull() {
        // Går igenom alla platser på brädet i = rader j = kolumer
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Om det finns någon plats som är ledig '-' är inte brädet fullt och spelet fortsätter
                if (board[i][j] == '-') {
                    return false; // Brädet är INTE fullt
                }
            }
        }
        return true; // Alla platser är fulla vilket betyder oavgjort
    }

    // Metod för att få giltig inmatning från användaren
    public int getValidInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            // Visar meddelande för att begära inmatning
            System.out.print(prompt);
            // Kontrollerar om inmatningen är ett giltigt heltal
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                // Kontrollerar om talet är inom det giltiga intervallet 1-3
                if (input >= 1 && input <= 3) {
                    return input - 1;  // Omvandla 1-3 till 0-2 för i Array index är det fortfarande från 0-2
                } else {
                    // Felmeddelande om talet inte är inom intervallet 1-3
                    System.out.println("Invalid input. Please enter a number between 1 and 3. Try Again!");
                }
            } else {
                // Felmeddelande om inmatningen inte är ett heltal
                System.out.println("Invalid input. Please enter a number between 1 and 3. Try Again!");
                scanner.next();  // Rensa felaktig inmatning
            }
        }
    }
}