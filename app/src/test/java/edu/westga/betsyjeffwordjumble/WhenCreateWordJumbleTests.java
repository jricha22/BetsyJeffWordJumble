package edu.westga.betsyjeffwordjumble;

import org.junit.Test;

import edu.westga.betsyjeffwordjumble.model.WordJumble;

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
        WordJumble myWordJumble = new WordJumble(100);
        assertNotNull(myWordJumble.getAWord());
    }

    /**
     * Test that retrieving 4 5 character words are all length is 5
     */
    @Test
    public void testRetrieveFourFiveCharWordsAreAllLengthFive() {
        WordJumble myWordJumble = new WordJumble(100);
        int total = 0;
        total += myWordJumble.getAFiveCharWord().length();
        total += myWordJumble.getAFiveCharWord().length();
        total += myWordJumble.getAFiveCharWord().length();
        total += myWordJumble.getAFiveCharWord().length();
        assertEquals(20, total);
    }

    /**
     * Test that retrieving 4 6 character words are all length is 6
     */
    @Test
    public void testRetrieveFourSixCharWordsAreAllLengthSix() {
        WordJumble myWordJumble = new WordJumble(100);
        int total = 0;
        total += myWordJumble.getASixCharWord().length();
        total += myWordJumble.getASixCharWord().length();
        total += myWordJumble.getASixCharWord().length();
        total += myWordJumble.getASixCharWord().length();
        assertEquals(24, total);
    }

    /**
     * Test that returned word is scrambled
     */
    @Test
    public void testReturnedWordIsScrambled() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("ahirc", myWordJumble.getAWord());
    }

    /**
     * Test that returned five char word is scrambled
     */
    @Test
    public void testReturnedFiveCharWordIsScrambled() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("bmera", myWordJumble.getAFiveCharWord());
    }

    /**
     * Test that returned five char word is scrambled
     */
    @Test
    public void testReturnedSixCharWordIsScrambled() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("ppeoel", myWordJumble.getASixCharWord());
    }

    /**
     * Test guessing correctly returns True
     */
    @Test
    public void testGuessWordCorrectlyIsTrue() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAWord();
        assertEquals(true, myWordJumble.checkResult("chair"));
    }

    /**
     * Test guessing five char word correctly returns True
     */
    @Test
    public void testGuessFiveCharWordCorrectlyIsTrue() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAFiveCharWord();
        assertEquals(true, myWordJumble.checkResult("amber"));
    }

    /**
     * Test guessing six char word correctly returns True
     */
    @Test
    public void testGuessSixCharWordCorrectlyIsTrue() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getASixCharWord();
        assertEquals(true, myWordJumble.checkResult("people"));
    }

    /**
     * Test guessing incorrectly returns False
     */
    @Test
    public void testGuessWordIncorrectlyIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAWord();
        assertEquals(false, myWordJumble.checkResult("xxxxx"));
    }

    /**
     * Test guessing five char word incorrectly returns False
     */
    @Test
    public void testGuessFiveCharWordIncorrectlyIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAFiveCharWord();
        assertEquals(false, myWordJumble.checkResult("xxxxx"));
    }

    /**
     * Test guessing incorrectly returns False
     */
    @Test
    public void testGuessSixCharWordIncorrectlyIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getASixCharWord();
        assertEquals(false, myWordJumble.checkResult("xxxxxx"));
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
     * Test guessing 5 char word null returns False
     */
    @Test
    public void testGuessFiveCharWordNullIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getAFiveCharWord();
        assertEquals(false, myWordJumble.checkResult(null));
    }

    /**
     * Test guessing 6 char word null returns False
     */
    @Test
    public void testGuessSixCharNullIsFalse() {
        WordJumble myWordJumble = new WordJumble(100);
        String jumbled = myWordJumble.getASixCharWord();
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

    /**
     * Test that the hint returns null if no word selected
     */
    @Test
    public void testHintIsNullBeforeWordRetrieved() {
        WordJumble myWordJumble = new WordJumble(100);
        assertNull(myWordJumble.getAHint());
    }

    /**
     * Test that the hint is correct for any length word retrieved
     */
    @Test
    public void testHintIsCorrectForAnyLengthWord() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("ahirc", myWordJumble.getAWord());
        assertEquals("* h * * *", myWordJumble.getAHint());
    }

    /**
     * Test that the hint is correct for 5 length word retrieved
     */
    @Test
    public void testHintIsCorrectForFiveLengthWord() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("bmera", myWordJumble.getAFiveCharWord());
        assertEquals("* m * * *", myWordJumble.getAHint());
    }

    /**
     * Test that the hint is correct for 6 length word retrieved
     */
    @Test
    public void testHintIsCorrectForSixLengthWord() {
        WordJumble myWordJumble = new WordJumble(100);
        assertEquals("ppeoel", myWordJumble.getASixCharWord());
        assertEquals("* * o * * *", myWordJumble.getAHint());
    }
}