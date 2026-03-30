// This part was done by Jocelyn Brown, 101597391!
import java.util.Scanner;

public class AI {

    // The game board is a simple 3 x 3 grid of characters.
    // "X" = human player.
    // "O" = AI player.
    // ' ' = Empty space.
    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    // Scanner for reading user input:
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the Tic Tac Toe game! You are X, your AI opponent is O.");

        // Main game loop - it will continue to run until someone wins or the board is full:
        while (true) {

            // Display the board before the human player's move:
            printBoard();

            // Human player makes a move:
            playerMove();

            // Check if the human player won:
            if (winner('X')) {
                printBoard();
                System.out.println("Congratulations! You are the winner!!");
                break; // End the game.
            }

            // Check for a draw (board full):
            if (full()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            // AI makes its move using the minimax algorithm:
            aiMove();

            // Check if the AI player won:
            if (winner('O')) {
                printBoard();
                System.out.println("The AI player has won this round!");
                break;
            }

            // Check for a draw again:
            if (full()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }
        }
    }

    // --------------------------------------------------------------------------
    // Printing the Board
    // --------------------------------------------------------------------------

    // Prints the current board in a simple grid format:
    static void printBoard() {
        System.out.println("\n  1 2 3"); // Column numbers

        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " "); // Row numbers
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]); // Print the cell
                if (j < 2) System.out.print("|"); // Vertical separators
            }
            System.out.println(); // Move on to the next row.
        }
        System.out.println();
    }

    // --------------------------------------------------------------------------
    // Human Move
    // --------------------------------------------------------------------------

    // Lets the human player select a row and column:
    static void playerMove() {
        while (true) {
            System.out.print("Your move (row column): ");

            // Convert user input (1-3) into array indices (0-2):
            int row = input.nextInt() - 1;
            int column = input.nextInt() - 1;

            // Check if the move is inside the board AND empty:
            if (row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == ' ') {
                board[row][column] = 'X'; // Place the human's move.
                return; // Exit the method
            }

            // If invalid, ask again:
            System.out.println("Invalid move! Please try again.");
        }
    }

    // --------------------------------------------------------------------------
    // Board Checks
    // --------------------------------------------------------------------------

    // Returns true if the board has no empty spaces left:
    static boolean full() {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ') return false; // Found an empty cell
        return true; // No empty cells found
    }

    // Checks if a given player ('X' or 'O') has won the game:
    static boolean winner(char player) {

        // Check all three rows:
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }

        // Check all three columns:
        for (int j = 0; j < 3; j++)
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }

        // Check main diagonal:
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        // Check opposite diagonal:
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false; // No winner found
    }

    // --------------------------------------------------------------------------
    // AI Move Using Minimax Algorithm
    // --------------------------------------------------------------------------

    static void aiMove() {

        // AI wants to maximize its score, so start with the lowest value possible:
        int bestScore = Integer.MIN_VALUE;

        // Store the best move found:
        int bestRow = -1, bestColumn = -1;

        // Try every possible empty cell:
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {

                if (board[row][column] == ' ') { // Only consider empty spots
                    board[row][column] = 'O'; // Try placing the AI move here

                    // Call minimax to evaluate this move.
                    // false = next turn is the human player's move.
                    int score = minimax(false);

                    board[row][column] = ' '; // Undo the move (backtracking)

                    // If this move is better than the previous best, keep it:
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestColumn = column;
                    }
                }
            }
        }

        // After checking all moves, place the best one:
        board[bestRow][bestColumn] = 'O';
    }

    // --------------------------------------------------------------------------
    // Minimax Algorithm (Recursive)
    // --------------------------------------------------------------------------

    /*
    minimax(aiTurn)

    aiTurn = true --> AI's turn (maximize score)
    aiTurn = false --> Human player's turn (minimize score)

    Returns:
        +10 if AI wins
        -10 if human player wins
          0 if there is a draw

    Otherwise, recursively explores all possible future moves.
    */

    static int minimax(boolean aiTurn) {

        //BASE CASES: If game is already decided, return the score immediately:
        if (winner('O')) return 10; // AI wins
        if (winner('X')) return -10; //Human player wins
        if (full()) return 0; // Game is a draw

        // --------------------------------------------------------------------------
        // AI Turn (Maximizing Player)
        // --------------------------------------------------------------------------
        if (aiTurn) {
            int bestMove = Integer.MIN_VALUE; // Start with the worst possible score

            // Try every empty cell:
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {

                    if (board[row][column] == ' ') {
                        board[row][column] = 'O'; // AI tries this move

                        // Recursively evaluate the result of this move
                        bestMove = Math.max(bestMove, minimax(false));

                        board[row][column] = ' '; // Undo move
                    }
                }
            }
            return bestMove;

        }

        // --------------------------------------------------------------------------
        // Human Player Turn (Minimizing Player)
        // --------------------------------------------------------------------------

        else {
            int bestMove = Integer.MAX_VALUE; // Start with the highest possible score

            // Try every empty cell:
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {

                    if (board[row][column] == ' ') {

                        board[row][column] = 'X'; // Human player tries this move

                        // Recursively evaluate the result of this move:
                        bestMove = Math.min(bestMove, minimax(true));

                        board[row][column] = ' '; // Undo move
                    }
                }
            }

            return bestMove; // Best score the human player can force.
        }
    }
}
