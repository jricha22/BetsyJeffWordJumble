package edu.westga.betsyjeffwordjumble;

import org.junit.Test;

import edu.betsyjeffwordjumble.model.WordJumble;

import static org.junit.Assert.*;

/**
 * Unit tests for WordJumble model creation
 */
public class WhenCreateWordJumbleTests {
    /**
     * Test that a word is returned after the model is created
     */
    @Test
    public void testProvidesAWordAfterCreation() {
        WordJumble myWordJumble = new WordJumble();
        assertNotNull(myWordJumble.getAWord());
    }


    /**
     * Test that default word length is 5
     */
    @Test
    public void testProvidesAFiveCharacterWordAfterCreation() {
        WordJumble myWordJumble = new WordJumble();
        assertEquals(5, myWordJumble.getAWord().length());
    }

    /**
     * Test that returned word is scrambled
     */
    @Test
    public void testReturnedWordIsScrambled() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("iuckq", myWordJumble.getAWord());
    }

    /**
     * Test guessing correctly returns True
     */
    @Test
    public void testGuessWordCorrectlyIsTrue() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAWord();
        assertEquals(true, myWordJumble.checkResult("quick"));
    }

    /**
     * Test guessing incorrectly returns False
     */
    @Test
    public void testGuessWordIncorrectlyIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAWord();
        assertEquals(false, myWordJumble.checkResult("meter"));
    }

    /**
     * Test guessing null returns False
     */
    @Test
    public void testGuessNullIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAWord();
        assertEquals(false, myWordJumble.checkResult(null));
    }

    /**
     * Test guessing null without getting word is False
     */
    @Test
    public void testGuessNullWithoutWordGetIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals(false, myWordJumble.checkResult(null));
    }
}