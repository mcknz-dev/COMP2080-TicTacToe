
// Main Menu
void main() {
    boolean playAgain = true;

    while (playAgain) {

        int mode = UI.promptGameMode();

        if (mode == 1) {
            TwoPlayer.playTwoPlayer();
        } else {
            AI.playVsAI();
        }

        playAgain = UI.promptPlayAgain();
    }
    System.out.println("Thanks for playing, goodbye!");
}