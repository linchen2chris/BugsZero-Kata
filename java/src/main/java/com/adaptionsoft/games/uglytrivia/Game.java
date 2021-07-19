package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList<String> players = new ArrayList<String>(); // max 6 players min 2players
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    int currentPlayer = 0;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(("Rock Question " + i));
        }
    }

    public boolean add(String playerName) {

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    private int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                movePlayerAndAskQuestion(roll);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");

            }

        } else {

            movePlayerAndAskQuestion(roll);
        }

    }

    private void movePlayerAndAskQuestion(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11)
            places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        switch (currentCategory()) {
        case "Pop":
            System.out.println(popQuestions.removeFirst());
            break;
        case "Science":
            System.out.println(scienceQuestions.removeFirst());
            break;
        case "Sports":
            System.out.println(sportsQuestions.removeFirst());
            break;
        case "Rock":
            System.out.println(rockQuestions.removeFirst());
            break;
        }
    }

    private String currentCategory() {
        switch (places[currentPlayer]) {
        case 0:
        case 4:
        case 8:
            return "Pop";
        case 1:
        case 5:
        case 9:
            return "Science";
        case 2:
        case 6:
        case 10:
            return "Sports";
        default:
            return "Rock";
        }
    }

    public boolean wasCorrectlyAnswered(Integer roll) {
        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                System.out.println("Answer was correct!!!!");
                setNextPlayer();
                purses[currentPlayer]++;
                System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

                boolean winner = didPlayerWin();

                return winner;
            } else {
                setNextPlayer();
                return true;
            }

        } else {

            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean winner = didPlayerWin();
            setNextPlayer();

            return winner;
        }
    }

    private void setNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size())
            currentPlayer = 0;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        setNextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
