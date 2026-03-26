
// Main Menu
public static void main(String[] args) {

    boolean playAgain = true;

    while (playAgain) {

        int mode = UI.promptGameMode();

        if (mode == 1) {
            playTwoPlayer();
        } else {
            playVsAI();
        }

        playAgain = UI.promptPlayAgain();
    }
    System.out.println("Thanks for playing, goodbye!");
}