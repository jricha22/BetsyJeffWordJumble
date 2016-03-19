package edu.westga.betsyjeffwordjumble;

import org.junit.Test;

import edu.betsyjeffwordjumble.model.WordJumble;

import static org.junit.Assert.*;

/**
 * Unit tests for WordJumble model creation
 */
public class WhenCreateWordJumbleTests {
    @Test
    public void testProvidesAWordAfterCreation() {
        WordJumble myWordJumble = new WordJumble();
        assertNotNull(myWordJumble.getAWord());
    }
}