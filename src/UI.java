//Created By:
//Mackenzie Hodgson
//101597352

import java.util.Scanner;

public class UI {

    static Scanner scanner = new Scanner(System.in);

    //Welcome Screen / Game mode selection -----------------------------------------------------------------------------
    public static int promptGameMode() {
        System.out.println("================================");
        System.out.println("     Welcome to Tic-Tac-Toe!   ");
        System.out.println("================================");
        System.out.println("Please select a game mode:");
        System.out.println("  1 - Two Players");
        System.out.println("  2 - Vs AI");
        System.out.println();

        int choice = 0;

        while (choice != 1 && choice != 2) {
            System.out.print("Enter 1 or 2: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice, please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input, please enter a number.");
                scanner.next();
            }
        }
        return choice;
    }

    //Asks player their name -------------------------------------------------------------------------------------------
    public static String promptName(int playerNumber) {
        String name = "";

        while (name.trim().isEmpty()) {
            System.out.print("Enter name for Player " + playerNumber + ": ");
            name = scanner.next();

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty, please try again.");
            }
        }

        return name;
    }

    //Asks a player to pick X or O -------------------------------------------------------------------------------------
    public static char promptSymbol(String playerName) {
        char symbol = ' ';

        while (symbol != 'X' && symbol != 'O') {
            System.out.print(playerName + ", choose your symbol (X or O): ");
            String input = scanner.next().toUpperCase();

            if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
                symbol = input.charAt(0);
            } else {
                System.out.println("Invalid choice, please enter X or O.");
            }
        }
        return symbol;
    }

    //Asks a player where they want to move ----------------------------------------------------------------------------
    public static int[] promptMove(String playerName) {
        int row = 0;
        int col = 0;

        System.out.println(playerName + ", enter your move:");

        while (row < 1 || row > 3) {
            System.out.print("  Row (1-3): ");

            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (row < 1 || row > 3) {
                    System.out.println("  Invalid row, please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("  Invalid input, please enter a number.");
                scanner.next();
            }
        }

        while (col < 1 || col > 3) {
            System.out.print("  Column (1-3): ");

            if (scanner.hasNextInt()) {
                col = scanner.nextInt();
                if (col < 1 || col > 3) {
                    System.out.println("  Invalid column, please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("  Invalid input, please enter a number.");
                scanner.next();
            }
        }

        return new int[]{row - 1, col - 1};
    }

    //Announces the winner ---------------------------------------------------------------------------------------------
    public static void showWinner(String playerName) {
        System.out.println("================================");
        System.out.println("  Congratulations " + playerName + "!");
        System.out.println("       You won the game!        ");
        System.out.println("================================");
    }

    //Announces a draw -------------------------------------------------------------------------------------------------
    public static void showDraw() {
        System.out.println("================================");
        System.out.println("         It's a draw!           ");
        System.out.println("      Well played both of you!  ");
        System.out.println("================================");
    }

    //Shows an error message -------------------------------------------------------------------------------------------
    public static void showError(String message) {
        System.out.println("Oops! " + message + " Please try again.");
    }

    //Asks if they want to play again ----------------------------------------------------------------------------------
    public static boolean promptPlayAgain() {
        String input = "";

        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("Would you like to play again? (Y or N): ");
            input = scanner.next().toUpperCase();

            if (!input.equals("Y") && !input.equals("N")) {
                System.out.println("Invalid input, please enter Y or N.");
            }
        }
        return input.equals("Y");
    }

}
