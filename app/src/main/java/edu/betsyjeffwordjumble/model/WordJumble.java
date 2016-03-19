package edu.betsyjeffwordjumble.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private List<String> wordsFive;
    private List<String> wordsSix;
    private Random randomGenerator;
    private String currentWord;

    /**
     * Default constructor.
     */
    public WordJumble() {
        this.randomGenerator = new Random();
        this.words = new ArrayList<>();
        this.wordsFive = new ArrayList<>();
        this.wordsSix = new ArrayList<>();
        InputStream wordStream = getClass().getClassLoader().getResourceAsStream("words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(wordStream));
        StringBuilder out = new StringBuilder();
        String word;
        try {
            while ((word = reader.readLine()) != null) {
                this.words.add(word.toLowerCase());
            }
            reader.close();
        }
        catch (IOException err) {
            // Nothing
        }
        for (String curr: this.words) {
            if (curr.length() == 5) {
                this.wordsFive.add(curr);
            }
            if (curr.length() == 6) {
                this.wordsSix.add(curr);
            }
        }
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

    private String scrambleWord(String word) {
        List<Character> characters = new ArrayList<>();
        for(char c :  word.toCharArray())
            characters.add(c);
        Collections.shuffle(characters, this.randomGenerator);
        StringBuilder myBuilder = new StringBuilder();
        for(char aChar : characters)
            myBuilder.append(aChar);
        return myBuilder.toString();
    }

    /**
     * Get a word from the word list that is randomly scrambled.
     * Remembers the selected word, can be checked by calling checkResult.
     * @return A word from the word list with letters randomly shuffled
     */
    public String getAWord() {
        this.currentWord = this.words.get(this.randomGenerator.nextInt(this.words.size()));
        return scrambleWord(this.currentWord);
    }

    /**
     * Get a five character word from the word list that is randomly scrambled.
     * Remembers the selected word, can be checked by calling checkResult.
     * @return A five character word from the word list with letters randomly shuffled
     */
    public String getAFiveCharWord() {
        this.currentWord = this.wordsFive.get(this.randomGenerator.nextInt(this.wordsFive.size()));
        return scrambleWord(this.currentWord);
    }

    /**
     * Get a six character word from the word list that is randomly scrambled.
     * Remembers the selected word, can be checked by calling checkResult.
     * @return A six character word from the word list with letters randomly shuffled
     */
    public String getASixCharWord() {
        this.currentWord = this.wordsSix.get(this.randomGenerator.nextInt(this.wordsSix.size()));
        return scrambleWord(this.currentWord);
    }

    /**
     * Check if a guessed word is the current word.
     * @param theGuess The word guessed
     * @return True if word is correct, false otherwise
     */
    public boolean checkResult(String theGuess) {
        return theGuess != null && theGuess.equals(this.currentWord);
    }
}
