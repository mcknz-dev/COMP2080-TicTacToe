import java.util.Scanner;

public class UI {

    static Scanner scanner = new Scanner(System.in);

    //Shows the board to the player
    public static void showBoard(){

    }

    //Welcome Screen / Game mode selection
    public static int promptGameMode() {

        return choice;
    }
    //Asks player their name
    public static String promptName(){

        return name;
    }

    //Asks a player to pick X or O
    public static char promptSymbol(){

        return symbol;
    }

    //Asks a player where they want to move
    public static int[] promptMove(){

        return new int[]{};
    }

    //Announces the winner
    public static void showWinner(){

    }

    //Announces a draw
    public static void showDraw(){

    }

    //Shows an error message
    public static void showError(){

    }

    //Asks if they want to play again
    public static boolean promptPlayAgain(){

        return input.equals();
    }

}
