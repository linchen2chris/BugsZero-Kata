package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameConfig {
    LinkedList<String> popQuestions = new LinkedList<String>();
    LinkedList<String> scienceQuestions = new LinkedList<String>();
    LinkedList<String> sportsQuestions = new LinkedList<String>();
    LinkedList<String> rockQuestions = new LinkedList<String>();

    final Map<Integer, String> placesQuestionMap = new HashMap<>();
    final Map<String, LinkedList<String>> categoryQuestionMap = new HashMap<>();
    final Integer MAX_COIN = 6;
    final Integer MAX_POSITON = 12;

    public GameConfig () {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(("Rock Question " + i));
        }
        placesQuestionMap.put(0, "Pop");
        placesQuestionMap.put(1, "Science");
        placesQuestionMap.put(2, "Sports");
        placesQuestionMap.put(3, "Rock");
        placesQuestionMap.put(4, "Pop");
        placesQuestionMap.put(5, "Science");
        placesQuestionMap.put(6, "Sports");
        placesQuestionMap.put(7, "Rock");
        placesQuestionMap.put(8, "Pop");
        placesQuestionMap.put(9, "Science");
        placesQuestionMap.put(10, "Sports");
        placesQuestionMap.put(11, "Rock");
        categoryQuestionMap.put("Pop", this.popQuestions);
        categoryQuestionMap.put("Science", this.scienceQuestions);
        categoryQuestionMap.put("Sports", this.sportsQuestions);
        categoryQuestionMap.put("Rock", this.rockQuestions);
    }

    public boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

}
