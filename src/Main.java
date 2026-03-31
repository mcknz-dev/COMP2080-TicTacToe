public class Main {

    public static void main(String[] args) {

        boolean playAgain = true;

        while (playAgain) {

            // Ask the user which mode they want to choose from:
            int mode = UI.promptGameMode();

            if (mode == 1) {
                // Two-player mode:
                TwoPlayer.playTwoPlayer();
            } else {
                // AI mode (minimax logic):
                playVsAI();
            }

            // Ask if they want to play again:
            playAgain = UI.promptPlayAgain();
        }

        System.out.println("Thanks for playing, goodbye!");
    }

    // --------------------------------------------------------------------------
    // AI Mode (Human = X, AI = O)
    // --------------------------------------------------------------------------

    public static void playVsAI() {

        BoardAlt board = new BoardAlt();
        char human = 'X';
        char ai = 'O';

        while (true) {

            // Show the board before the human player's move:
            UI.showBoard(board.getBoardArray());

            // Human player move:
            int[] move = UI.promptMove("Player");
            board.placeSymbol(human, move[0], move[1]);

            // Check if human player wins:
            if (board.checkWin(human)) {
                UI.showBoard(board.getBoardArray());
                UI.showWinner("Player");
                break;
            }

            // Check for a draw:
            if (board.checkState(human) == 'D') {
                UI.showBoard(board.getBoardArray());
                UI.showDraw();
                break;
            }

            // AI move (using minimax logic):
            int[] aiMove = AI.getBestMove(board);
            board.placeSymbol(ai, aiMove[0], aiMove[1]);

            // Check if AI wins:
            if (board.checkWin(ai)) {
                UI.showBoard(board.getBoardArray());
                System.out.println("================================");
                System.out.println("            AI wins!            ");
                System.out.println("================================");
                break;
            }

            // Check for a draw again:
            if (board.checkState(ai) == 'D') {
                UI.showBoard(board.getBoardArray());
                UI.showDraw();
                break;
            }
        }
    }
}