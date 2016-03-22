package edu.westga.betsyjeffwordjumble.viewTests;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.view.GameScreenActivity;
import edu.westga.betsyjeffwordjumble.view.ResultsActivity;

/**
 * Created by Betsy on 3/18/2016.
 */
public class GameScreenActivityTests extends ActivityInstrumentationTestCase2<GameScreenActivity> {

    public final static String LETTER_COUNT = "edu.westga.betsyjeffwordjumble.LETTER_COUNT";

    private GameScreenActivity m_gameScreenActivity;
    private Button m_btnEnter;
    private EditText m_etAnswer;
    private TextView m_tvScrambledWord;

    public GameScreenActivityTests() {
        super(GameScreenActivity.class);
    }

    @Override
    protected void setUp() {
        Intent intent = new Intent();
        intent.putExtra(LETTER_COUNT, 5);
        setActivityIntent(intent);
        m_gameScreenActivity = getActivity();
        m_btnEnter = (Button) m_gameScreenActivity.findViewById(R.id.btnEnter);
        m_etAnswer = (EditText) m_gameScreenActivity.findViewById(R.id.etAnswer);
        m_tvScrambledWord = (TextView) m_gameScreenActivity.findViewById(R.id.tvScrambledWord);
    }

    public void testActivityExists() {
        assertNotNull(m_gameScreenActivity);
    }

    public void testEnterButtonIsDisabled() {
        assertFalse(m_btnEnter.isEnabled());
    }

    private void enterAnswer(String answerText) {
        final String text = answerText;
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                GameScreenActivityTests.this.m_etAnswer.setText(text);
            }
        });
    }

    public void testEnterButtonIsDisabledWhenEmptyStringIsEntered() {
        enterAnswer(" ");
        assertFalse(m_btnEnter.isEnabled());
    }

    private void testEnterButtonIsEnabledWhenNonEmptyStringIsEntered() {
        enterAnswer("t");
        assertTrue(m_btnEnter.isEnabled());
    }

    public void testEnterButtonLoadsResultActivity() {
        // register next activity that needs to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(ResultsActivity.class.getName(), null, false);
        // Enter an answer
        enterAnswer("t");
        // Tap enter button
        TouchUtils.clickView(this, m_btnEnter);
        ResultsActivity nextActivity = (ResultsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Result screen activity is not launched", nextActivity);
        nextActivity .finish();
    }

    public void testTextViewHasScrambledWord() {
       assertTrue("Scrambled word not displayed", m_tvScrambledWord.getText().toString().trim().length() > 0);
    }

    public void testLetterCountIntentExtraIsReceived() {
        int intentExtra = m_gameScreenActivity.getIntent().getIntExtra(LETTER_COUNT, 0);
        assertEquals(5, intentExtra);
    }

    public void testFiveLetterScrambledWordIsDisplayedWhenIntentExtraIs5() {
        int intentExtra = m_gameScreenActivity.getIntent().getIntExtra(LETTER_COUNT, 0);
        assertTrue("Not a five letter word", m_tvScrambledWord.getText().length() == 5);
    }

    public void testSixLetterScrambledWordIsDisplayedWhenIntentExtraIs6() {
        m_gameScreenActivity.finish();  // close the activity
        setActivity(null); // forces next call of getActivity to re-open the activity
        Intent intent = new Intent();
        intent.putExtra(LETTER_COUNT, 6);
        setActivityIntent(intent);
        m_gameScreenActivity = getActivity();
        m_tvScrambledWord = (TextView) m_gameScreenActivity.findViewById(R.id.tvScrambledWord);
        int intentExtra = m_gameScreenActivity.getIntent().getIntExtra(LETTER_COUNT, 0);
        assertTrue("Not a six letter word", m_tvScrambledWord.getText().length() == 6);
    }
}
