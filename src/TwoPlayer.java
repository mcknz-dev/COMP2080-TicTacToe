//Created By:
//Derek Page
//101590623

public class TwoPlayer {
    private static final Board board = new Board();
    private static final String[] players = new String[2];
    private static char[] symbols = new char[2];

    public static void playTwoPlayer() {

        // Get player names using UI
        players[0] = UI.promptName(1);
        players[1] = UI.promptName(2);

        // Get player 1 symbol, player 2 gets the other
        symbols[0] = UI.promptSymbol(players[0]);
        symbols[1] = (symbols[0] == 'X') ? 'O' : 'X';
        System.out.println(players[1] + " you are playing as: " + symbols[1]);

        // X always goes first
        int player = (symbols[0] == 'X') ? 0 : 1;

        // Show empty board before first move
        board.showBoard();

        char winner = 'N';

        while (winner == 'N') {

            char symbol = symbols[player];

            // Get move using UI
            int[] move = UI.promptMove(players[player]);
            int row = move[0];
            int col = move[1];

            if (board.placeSymbol(symbol, row, col)) {
                board.showBoard();
                winner = board.checkState(symbol);
                player = 1 - player;
            } else {
                board.showBoard();
                UI.showError("That cell is already taken.");
            }
        }

        // Announce result using UI
        if (winner == symbols[0]) {
            UI.showWinner(players[0]);
        } else if (winner == symbols[1]) {
            UI.showWinner(players[1]);
        } else {
            UI.showDraw();
        }

        board.resetBoard();
    }
}