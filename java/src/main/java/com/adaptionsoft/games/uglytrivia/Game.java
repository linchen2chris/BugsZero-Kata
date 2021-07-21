package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Game {
    final int MAX_LOCATION = 12;
    ArrayList<String> players = new ArrayList<String>(); // max 6 players min 2players
    int[] places = new int[6]; // palyers 的走位
    int[] purses = new int[6]; // 每个player的金币数
    boolean[] inPenaltyBox = new boolean[6]; // 记录是否被处罚
    GameConfig config = new GameConfig();

    int currentPlayer = 0;

    public Game() {}

    public void add(String playerName) {

        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + howManyPlayers());
    }

    private int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (gettingOutOfPenaltyBox(roll)) {
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                movePlayerAndAskQuestion(roll);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            }
        } else {
            movePlayerAndAskQuestion(roll);
        }
    }

    private boolean gettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private void movePlayerAndAskQuestion(int roll) {
        places[currentPlayer] = (places[currentPlayer] + roll) % this.MAX_LOCATION;

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        String category = config.placesQuestionMap.get(places[currentPlayer]);
        System.out.println("The category is " + category);
        System.out.println(config.categoryQuestionMap.get(category).removeFirst());
    }


    public boolean wasCorrectlyAnswered(Integer roll) {
        if (inPenaltyBox[currentPlayer] && !gettingOutOfPenaltyBox(roll)) {
            setNextPlayer();
            return false;
        }

        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

        boolean winner = didPlayerWin();
        setNextPlayer();

        return winner;
    }

    private void setNextPlayer() {
        currentPlayer = (currentPlayer + 1)% howManyPlayers();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        setNextPlayer();
        return false;
    }

    private boolean didPlayerWin() {
        int MAX_COINS = 6;
        return purses[currentPlayer] == MAX_COINS;
    }
}
