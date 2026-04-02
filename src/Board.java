//Created By:
//John Kenny
//101577733

public class Board {

    // --------------------------------------------------------------------------
    // Board.java contains game board data and methods
    // --------------------------------------------------------------------------

    // stores current state of game board
    private final char[][] boardArray = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    // stores the amount of moves in current game
    private int moveCount = 0;

    // shows game board
    public void showBoard() {

        System.out.println("    1   2   3 ");
        System.out.println("   -----------");
        System.out.printf("1 | %c | %c | %c |\n", boardArray[0][0], boardArray[0][1], boardArray[0][2]);
        System.out.println("   -----------");
        System.out.printf("2 | %c | %c | %c |\n", boardArray[1][0], boardArray[1][1], boardArray[1][2]);
        System.out.println("   -----------");
        System.out.printf("3 | %c | %c | %c |\n", boardArray[2][0], boardArray[2][1], boardArray[2][2]);
        System.out.println("   -----------");
        System.out.println();
    }

    public char[][] getBoardArray() {
        return boardArray;
    }

    // places symbol within game board
    public boolean placeSymbol(char symbol, int row, int col) {
        if (boardArray[row][col] == ' ') {
            boardArray[row][col] = symbol;
            moveCount++;
            return true;
        } else {
            return false;
        }
    }

    // checks if a certain symbol (X or O) has won
    public boolean checkWin(char symbol){
        for (int i = 0; i < 3; i++) {
            if (boardArray[i][0] == symbol && boardArray[i][1] == symbol && boardArray[i][2] == symbol) {
                return true;
            }
            else if (boardArray[0][i] == symbol && boardArray[1][i] == symbol && boardArray[2][i] == symbol) {
                return true;
            }
        }
        return boardArray[1][1] == symbol &&
                (boardArray[0][0] == symbol && boardArray[2][2] == symbol || boardArray[0][2] == symbol && boardArray[2][0] == symbol);
    }

    // returns state of game board
    public char checkState(char symbol){
        if (checkWin(symbol)) return symbol;
        else if (moveCount == 9) return 'D';
        else return 'N';
    }

    // resets the game board
    public void resetBoard() {
        for  (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardArray[i][j] = ' ';
            }
        }
        moveCount = 0;
    }
}
