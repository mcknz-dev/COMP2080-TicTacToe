//Created By:
//Jocelyn Brown
//101597391

public class AI {

        // --------------------------------------------------------------------------
        // AI Game Mode - called by Main.java
        // --------------------------------------------------------------------------

        public static void playVsAI() {

            // Get player name and symbol
            String playerName = UI.promptName(1);
            char human = UI.promptSymbol(playerName);
            char ai = (human == 'X') ? 'O' : 'X';
            System.out.println("Computer is playing as: " + ai);

            Board board = new Board();
            board.showBoard();

            // X always goes first
            boolean isPlayerTurn = (human == 'X');

            while (true) {

                if (isPlayerTurn) {

                    int[] move = UI.promptMove(playerName);
                    board.placeSymbol(human, move[0], move[1]);

                    if (board.checkWin(human)) {
                        board.showBoard();
                        UI.showWinner(playerName);
                        break;
                    }

                    if (board.checkState(human) == 'D') {
                        board.showBoard();
                        UI.showDraw();
                        break;
                    }

                    board.showBoard();

                } else {

                    System.out.println("Computer is thinking...");
                    int[] aiMove = AI.getBestMove(board);
                    board.placeSymbol(ai, aiMove[0], aiMove[1]);
                    board.showBoard();

                    if (board.checkWin(ai)) {
                        System.out.println("================================");
                        System.out.println("         The AI wins!           ");
                        System.out.println("     Better luck next time!     ");
                        System.out.println("================================");
                        break;
                    }

                    if (board.checkState(ai) == 'D') {
                        board.showBoard();
                        UI.showDraw();
                        break;
                    }
                }

                isPlayerTurn = !isPlayerTurn;
            }

            board.resetBoard();
        }

        // --------------------------------------------------------------------------
        // Get Best Move - returns the AI's optimal move
        // --------------------------------------------------------------------------

        public static int[] getBestMove(Board board) {

            char[][] boardArray = board.getBoardArray();

            int bestScore = Integer.MIN_VALUE;
            int bestRow = -1;
            int bestCol = -1;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {

                    if (boardArray[row][col] == ' ') {

                        boardArray[row][col] = 'O';
                        int score = minimax(boardArray, false);
                        boardArray[row][col] = ' ';

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

            if (winner(board, 'O')) 
                    return 10;
            if (winner(board, 'X')) 
                    return -10;
            if (full(board)) 
                    return 0;

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

        private static boolean full(char[][] board) {
            for (char[] row : board)
                for (char cell : row)
                    if (cell == ' ')
                        return false;
            return true;
        }

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
