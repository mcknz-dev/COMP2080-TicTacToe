/*
Author: John

 */


// TODO: I just created the basic logic. idk if we need to add input validation and exception handling stuff.
// TODO: please let me know if you like for me to do things differently. Lots of ways to do it differently..


public class Board {

    // I named the variable boardArray because I wanted the name to be easily differentiable from the class,
    // and i couldn't think of a better name idk
    private char[][] boardArray = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };


    public Board() {

    }

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


    // For this method 2 ideas came to mind. Either a symbol gets passed to it as parameter, so it knows which symbol
    // to add to the boardArray. or it just keeps track of what turn it is and alternates adding an X or an O.
    // I went with the first option just for this example.
    public boolean placeSymbol(char symbol, int row, int col) {
        if (boardArray[row][col] == ' ') {
            boardArray[row][col] = symbol;
            return true;
        } else {
            return false;
        }
    }

    // I created 3 different versions for the checkWin method.
    // the first one is a brute force method that checks every possible line for a 3-in-a-row
    // the second one is a more efficient version that does less checks.
    // third one is different because it takes as a parameter the row and col of the last symbol to be entered, which
    // makes the search more efficient


//    public boolean checkWin(char symbol){
//        // checks rows for 3-in-a-row
//        for (int row = 0; row < 3; row++) {
//            if (boardArray[row][0] == symbol && boardArray[row][1] == symbol && boardArray[row][2] == symbol)
//                return true;
//        }
//        // checks cols for 3-in-a-row
//        for (int col = 0; col < 3; col++) {
//            if (boardArray[0][col] == symbol && boardArray[1][col] == symbol && boardArray[2][col] == symbol)
//                return true;
//        }
//        // checks diagonals for 3-in-a-row
//        if (boardArray[0][0] == symbol && boardArray[1][1] == symbol && boardArray[2][2] == symbol)
//            return true;
//        if (boardArray[2][0] == symbol && boardArray[1][1] == symbol && boardArray[0][2] == symbol)
//            return true;
//
//        return false;
//    }


    public boolean checkWin(char symbol){
        // checks if the symbol in the top left [0][0] is the correct symbol and then
        // checks any 3-in-a-row that contain it
        if (boardArray[0][0] == symbol){
            if (boardArray[0][1] == symbol && boardArray[0][2] == symbol)
                return true;
            if (boardArray[1][0] == symbol && boardArray[2][0] == symbol)
                return true;
            if (boardArray[1][1] == symbol && boardArray[2][2] == symbol)
                return true;
        }
        // checks if the symbol in the very middle [1][1] is the correct symbol and then
        // checks any 3-in-a-row that contain it
        if (boardArray[1][1] == symbol){
            if (boardArray[1][0] == symbol && boardArray[1][2] == symbol)
                return true;
            if (boardArray[0][1] == symbol && boardArray[2][1] == symbol)
                return true;
            if (boardArray[0][2] == symbol && boardArray[2][0] == symbol)
                return true;
        }
        // checks if the symbol in the bottom right [2][2] is the correct symbol and then
        // checks any 3-in-a-row that contain it
        if (boardArray[2][2] == symbol){
            if (boardArray[2][0] == symbol && boardArray[2][1] == symbol)
                return true;
            if (boardArray[0][2] == symbol && boardArray[1][2] == symbol)
                return true;
        }
        return false;
    }



//    public boolean checkWin(char symbol, int row, int col) {
//        // Checks row that last entered symbol is in
//        if (boardArray[row][0] == symbol && boardArray[row][1] == symbol && boardArray[row][2] == symbol)
//            return true;
//        // Checks column that last entered symbol is in
//        if (boardArray[0][col] == symbol && boardArray[1][col] == symbol && boardArray[2][col] == symbol)
//            return true;
//        // checks diagonals for 3-in-a-row
//        if (boardArray[0][0] == symbol && boardArray[1][1] == symbol && boardArray[2][2] == symbol)
//            return true;
//        if (boardArray[2][0] == symbol && boardArray[1][1] == symbol && boardArray[0][2] == symbol)
//            return true;
//        return false;
//    }

    public boolean checkDraw(){
        int blanks = 0;
        for (int row = 0; row < boardArray.length; row++) {
            for (int col = 0; col < boardArray[row].length; col++) {
                if (boardArray[row][col] == ' ')
                    blanks++;
            }
        }
        if (blanks > 0){
            return false;
        } else  {
            return true;
        }
    }

    // maybe getBoardState and checkWin shouldn't receive symbols as parameters. I would have to change the code.
    public String getBoardState(char symbol){
        String state = "neither";
        if (checkWin(symbol)) state = "win";
        if (checkDraw()) state = "draw";
        return state;
    }





}
