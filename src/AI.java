//Created By:
//Jocelyn Brown
//101597391

public class AI {

        // --------------------------------------------------------------------------
        // AI Game Mode - called by Main.java
        // Handles the full game loop when playing against the computer.
        // Human chooses symbol, AI takes the opposite.
        // --------------------------------------------------------------------------

        public static void playVsAI() {

            // Get the player name and symbol:
            String playerName = UI.promptName(1);
            char human = UI.promptSymbol(playerName);
            char ai = (human == 'X') ? 'O' : 'X';
            System.out.println("Computer is playing as: " + ai);

            Board board = new Board();
            board.showBoard();

            // X always goes first:
            boolean isPlayerTurn = (human == 'X');

            while (true) {

                // ------------------------------------------------------------------
                // HUMAN TURN
                // ------------------------------------------------------------------
                if (isPlayerTurn) {

                    int[] move = UI.promptMove(playerName);

                    // Prevent illegal moves (cell already taken):
                    if (!board.placeSymbol(human, move[0], move[1])) {
                        board.showBoard();
                        UI.showError("That cell is already taken.");
                        continue; // Do NOT switch turns — ask again
                    }

                    // Check if the human player wins:
                    if (board.checkWin(human)) {
                        board.showBoard();
                        UI.showWinner(playerName);
                        break;
                    }

                    // Check for a draw:
                    if (board.checkState(human) == 'D') {
                        board.showBoard();
                        UI.showDraw();
                        break;
                    }

                    board.showBoard();
                }

                // ------------------------------------------------------------------
                // AI TURN
                // ------------------------------------------------------------------
                    else {

                    System.out.println("Computer is thinking...");

                    // AI chooses best move using the minimax algorithm:
                    int[] aiMove = AI.getBestMove(board);

                    // AI always plays legally because the minimax algorithm only selects empty cells:
                    board.placeSymbol(ai, aiMove[0], aiMove[1]);
                    board.showBoard();

                    if (board.checkWin(ai)) {
                        System.out.println("================================");
                        System.out.println("         The AI wins!           ");
                        System.out.println("     Better luck next time!     ");
                        System.out.println("================================");
                        break;
                    }

                    // Check for a draw:
                    if (board.checkState(ai) == 'D') {
                        board.showBoard();
                        UI.showDraw();
                        break;
                    }
                }

                // Switch turns:
                isPlayerTurn = !isPlayerTurn;
            }

            board.resetBoard();
        }

        // --------------------------------------------------------------------------
        // Get Best Move - returns the AI's optimal move using the minimax algorithm
        // --------------------------------------------------------------------------

        public static int[] getBestMove(Board board) {

            char[][] boardArray = board.getBoardArray();

            int bestScore = Integer.MIN_VALUE;
            int bestRow = -1;
            int bestCol = -1;

            // Try every empty cell on the board:
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (boardArray[row][col] == ' ') {

                        // Try the move:
                        boardArray[row][col] = 'O';

                        // Evaluate using the minimax algorithm:
                        int score = minimax(boardArray, false);

                        // Undo the move:
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
        // Evaluates all possible future game states and returns the best score.
        // AI tries to maximize score; human player tries to minimize it.
        // --------------------------------------------------------------------------

        private static int minimax(char[][] board, boolean aiTurn) {

            //Base Cases:
            if (winner(board, 'O')) 
                    return 10;    // AI wins
            if (winner(board, 'X')) 
                    return -10;  // Human player wins
            if (full(board)) 
                    return 0;   // Draw

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

            // Human turn (minimize score):
            } else {
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
        // Helper Methods
        // --------------------------------------------------------------------------

        // Returns true if the board is full:
        private static boolean full(char[][] board) {
            for (char[] row : board)
                for (char cell : row)
                    if (cell == ' ')
                        return false;
            return true;
        }

        // Checks if a given player has won:
        private static boolean winner(char[][] b, char p) {
            for (int i = 0; i < 3; i++) {
                if (b[i][0] == p && b[i][1] == p && b[i][2] == p) 
                        return true;
                if (b[0][i] == p && b[1][i] == p && b[2][i] == p) 
                        return true;
            }
            if (b[0][0] == p && b[1][1] == p && b[2][2] == p) 
                    return true;
            if (b[0][2] == p && b[1][1] == p && b[2][0] == p) 
                    return true;
            return false;
        }
    }
