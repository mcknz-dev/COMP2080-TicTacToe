// This part was done by Jocelyn Brown, 101597391!
// AI logic for Tic Tac Toe using the Minimax algorithm.
// Human is always 'X', AI is always 'O'.

public class AI {

    // Public method called by Main.java to get the AI's best move.
    // Returns an int[]{row, col}.
    public static int[] getBestMove(BoardAlt board) {

        char[][] boardArray = board.getBoardArray();

        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        // Try every empty cell on the board:
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                if (boardArray[row][col] == ' ') {

                    // Try the given move:
                    boardArray[row][col] = 'O';

                    // Evaluate using the minimax algorithm:
                    int score = minimax(boardArray, false);

                    // Undo move:
                    boardArray[row][col] = ' ';

                    // Keep the best scoring move:
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        return new int[]{bestRow, bestCol};
    }

    // --------------------------------------------------------------------------
    // Minimax Algorithm (Recursive)
    // --------------------------------------------------------------------------

    private static int minimax(char[][] board, boolean aiTurn) {

        // Base cases
        if (winner(board, 'O'))
            return 10;   // AI wins
        if (winner(board, 'X'))
            return -10;  // Human wins
        if (full(board))
            return 0;    // Draw

        // AI turn (maximize score):
        if (aiTurn) {
            int best = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board[row][col] == ' ') {
                        board[row][col] = 'O';
                        best = Math.max(best, minimax(board, false));
                        board[row][col] = ' ';
                    }
                }
            }
            return best;
        }

        // Human player turn (minimize score):
        else {
            int best = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (board[row][col] == ' ') {
                        board[row][col] = 'X';
                        best = Math.min(best, minimax(board, true));
                        board[row][col] = ' ';
                    }
                }
            }
            return best;
        }
    }

    // --------------------------------------------------------------------------
    // Helper Methods (local to AI)
    // --------------------------------------------------------------------------

    private static boolean full(char[][] board) {
        for (char[] row : board)
            for (char cell : row)
                if (cell == ' ')
                    return false;
        return true;
    }

    private static boolean winner(char[][] b, char p) {

        // Rows + Columns
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == p && b[i][1] == p && b[i][2] == p)
                return true;
            if (b[0][i] == p && b[1][i] == p && b[2][i] == p)
                return true;
        }

        // Diagonals
        if (b[0][0] == p && b[1][1] == p && b[2][2] == p)
            return true;
        if (b[0][2] == p && b[1][1] == p && b[2][0] == p)
            return true;

        return false;
    }
}
