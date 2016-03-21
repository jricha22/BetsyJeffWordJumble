package edu.westga.betsyjeffwordjumble.controller;

import edu.westga.betsyjeffwordjumble.model.WordJumble;

/**
 * Interface between model and view
 * Created by Betsy on 3/21/2016.
 */
public class Controller {
    private WordJumble m_wordJumble;

    public Controller() {
        m_wordJumble = new WordJumble();
    }

    public String getAWord() {
        return m_wordJumble.getAWord();
    }

    public boolean checkResult(String theGuess) {
        return m_wordJumble.checkResult(theGuess);
    }

}
