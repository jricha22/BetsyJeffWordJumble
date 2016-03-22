package edu.westga.betsyjeffwordjumble.controller;

import edu.westga.betsyjeffwordjumble.model.WordJumble;

/**
 * Interface between model and view
 * Created by Betsy on 3/21/2016.
 */
public class Controller {
    private WordJumble m_wordJumble;
    private String m_theWord;

    public Controller() {
        m_wordJumble = new WordJumble();
    }

    public String getTheWord() {
        return m_theWord;
    }

    public void setTheWord(String theWord) {
        m_theWord = theWord;
    }

    public String getAWord() {
        m_theWord = m_wordJumble.getAWord();
        return m_theWord;
    }

    public boolean checkResult(String theGuess) {
        return m_wordJumble.checkResult(theGuess);
    }

    public String getAFiveCharWord() {
        m_theWord = m_wordJumble.getAFiveCharWord();
        return m_theWord;
    }

    public String getASixCharWord() {
        m_theWord = m_wordJumble.getASixCharWord();
        return m_theWord;
    }

    public String getAHint() {
        return m_wordJumble.getAHint().toUpperCase();
    }

    public String getLetterAtPosition(int position) {
        String word = m_theWord;
        if (position > word.length() - 1) {
            throw new IllegalArgumentException("Invalid position in word");
        }
        return Character.toString(word.charAt(position)).toUpperCase();
    }

}
