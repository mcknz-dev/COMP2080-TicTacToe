// Mackenzie Hodgson - 101597352
// Jocelyn Brown - 101597391
// John -
// Derek -

public class Main {

    public static void main(String[] args) {

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
}