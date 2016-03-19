package edu.betsyjeffwordjumble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Model for a word jumble. Provides random jumbled words and solution checking.
 *
 * Created by Jeff on 3/19/2016.
 */

public class WordJumble {
    private List<String> words;
    private Random randomGenerator;
    private String currentWord;

    /**
     * Default constructor.
     */
    public WordJumble() {
        this.randomGenerator = new Random();
        words = new ArrayList<>();
        this.words.add("ocean");
        this.words.add("greet");
        this.words.add("quick");
        this.words.add("jumbo");
    }

    /**
     * Constructor that accepts seed for random number generation.
     * Useful for testing
     * @param seed Long integer to seed random number generator
     */
    public WordJumble(long seed) {
        this();
        this.randomGenerator = new Random(seed);
    }

    /**
     * Get a word from the word list that is randomly scrambled.
     * Remembers the selected word, can be checked by calling checkResult.
     * @return A word from the word list with letters randomly shuffled
     */
    public String getAWord() {
        this.currentWord = this.words.get(this.randomGenerator.nextInt(this.words.size()));
        List<Character> characters = new ArrayList<>();
        for(char c :  this.currentWord.toCharArray())
            characters.add(c);
        Collections.shuffle(characters, this.randomGenerator);
        StringBuilder myBuilder = new StringBuilder();
        for(char aChar : characters)
            myBuilder.append(aChar);
        return myBuilder.toString();
    }

    /**
     * Check if a guessed word is the current word.
     * @param theGuess The word guessed
     * @return True if word is correct, false otherwise
     */
    public boolean checkResult(String theGuess) {
        return theGuess.equals(this.currentWord);
    }
}
