package edu.westga.betsyjeffwordjumble.controller;

import edu.westga.betsyjeffwordjumble.model.WordJumble;

/**
 * Interface between model and view
 * Created by Betsy on 3/21/2016.
 */
public class Controller {
    private WordJumble mWordJumble;

    public Controller() {
        mWordJumble = new WordJumble();
    }

    public String getAWord() {
        return mWordJumble.getAWord();
    }

    public boolean checkResult(String theGuess) {
        return mWordJumble.checkResult(theGuess);
    }

}
