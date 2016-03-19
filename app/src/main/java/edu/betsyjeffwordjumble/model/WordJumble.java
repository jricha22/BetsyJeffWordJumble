package edu.betsyjeffwordjumble.model;

import java.util.ArrayList;
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
     * Get a word from the word list
     * @return A word from the word list
     */
    public String getAWord() {
        return this.words.get(this.randomGenerator.nextInt(this.words.size()));
    }
}
