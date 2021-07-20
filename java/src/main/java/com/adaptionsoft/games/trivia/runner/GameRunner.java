package com.adaptionsoft.games.trivia.runner;

import java.util.Random;
import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {

    private static boolean didWin;

    public static void main(String[] args) {
        Random rand = new Random();
        playGame(rand);
    }

    public static void playGame(Random rand) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {
            int roll = rand.nextInt(5) + 1;
            aGame.roll(roll); // 1,2,3,4,5

            if (rand.nextInt(9) == 7) { // 0 ... 8
                didWin = aGame.wrongAnswer();
            } else {
                didWin = aGame.wasCorrectlyAnswered(roll);
            }
        } while (!didWin);
    }
}
