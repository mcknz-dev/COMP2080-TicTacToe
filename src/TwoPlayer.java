import java.util.Scanner;

public class TwoPlayer {
    private static final BoardAlt board = new BoardAlt();

    private static final Scanner input = new Scanner(System.in);

    private static final String[] players =  new String[2];

    public static void playTwoPlayer() {
        System.out.println();
        System.out.println("Welcome to Two Player Tic Tac Toe!");
        System.out.println("Please enter the name for player O");
        players[0] = input.nextLine();
        System.out.println("Please enter the name for player X");
        players[1] = input.nextLine();
        System.out.println();
        int player = 0;
        char winner = 'N';
        while (winner == 'N') {

            char symbol = player == 0 ? 'O' : 'X';
            int row, col;

            System.out.printf("%s (%c), please enter a row (1 - 3)%n", players[player], symbol);
            while (true) {
                try  {
                    row = input.nextInt() - 1;
                }
                catch (Exception e) {
                    row = -1;
                }
                if (row >= 0 && row < 3) break;
                System.out.println("Invalid row. Please try again. (1 - 3)");
            }

            System.out.println("Please enter a column (1 - 3)");
            while (true) {
                try  {
                    col = input.nextInt() - 1;
                }
                catch (Exception e) {
                    col = -1;
                }
                if (col >= 0 && col < 3) break;
                System.out.println("Invalid column. Please try again. (1 - 3)");
            }

            if(board.placeSymbol(symbol, row, col)) {
                board.showBoard();
                winner = board.checkState(symbol);
                player = 1 - player;
            }
            else {
                board.showBoard();
                System.out.println("Cell already used. Please try again.");
            }
        }
        if(winner == 'O') {
            System.out.println(players[0] + " has won!");
        }
        else if(winner == 'X') {
            System.out.println(players[1] + " has won!");
        }
        else  {
            System.out.println("It's a draw!");
        }
        System.out.println();
        board.resetBoard();
    }

}
