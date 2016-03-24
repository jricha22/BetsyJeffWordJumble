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
 * Instrumentation tests for GameScreenActivity class.
 * Created by Betsy on 3/18/2016.
 */
public class GameScreenActivityTests extends ActivityInstrumentationTestCase2<GameScreenActivity> {

    public final static String LETTER_COUNT = "edu.westga.betsyjeffwordjumble.LETTER_COUNT";

    private GameScreenActivity m_gameScreenActivity;
    private Button m_btnSubmit;
    private EditText m_etAnswer;
    private Button m_btnHint;
    private TextView m_tvFirstLetter;
    private TextView m_tvSecondLetter;
    private TextView m_tvThirdLetter;
    private TextView m_tvFourthLetter;
    private TextView m_tvFifthLetter;
    private TextView m_tvSixthLetter;

    public GameScreenActivityTests() {
        super(GameScreenActivity.class);
    }

    @Override
    protected void setUp() {
        Intent intent = new Intent();
        intent.putExtra(LETTER_COUNT, 5);
        setActivityIntent(intent);
        m_gameScreenActivity = getActivity();
        m_btnSubmit = (Button) m_gameScreenActivity.findViewById(R.id.btnSubmit);
        m_etAnswer = (EditText) m_gameScreenActivity.findViewById(R.id.etAnswer);
        m_btnHint = (Button) m_gameScreenActivity.findViewById(R.id.btnHint);
        m_tvFirstLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvFirstLetter);
        m_tvSecondLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvSecondLetter);
        m_tvThirdLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvThirdLetter);
        m_tvFourthLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvFourthLetter);
        m_tvFifthLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvFifthLetter);
        m_tvSixthLetter = (TextView)m_gameScreenActivity.findViewById(R.id.tvSixthLetter);
    }

    public void testActivityExists() {
        assertNotNull(m_gameScreenActivity);
    }

    public void testSubmitButtonIsDisabled() {
        assertFalse("Submit button is not disabled.", m_btnSubmit.isEnabled());
    }

    public void testHintButtonIsEnabled() {
        assertTrue("Hint button is not enabled.", m_btnHint.isEnabled());
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

    public void testSubmitButtonIsDisabledWhenEmptyStringIsEntered() {
        enterAnswer(" ");
        assertFalse("Submit button is enabled.", m_btnSubmit.isEnabled());
    }

    private void testSubmitButtonIsEnabledWhenNonEmptyStringIsEntered() {
        enterAnswer("t");
        assertTrue("Submit button is not enabled.", m_btnSubmit.isEnabled());
    }

    public void testSubmitButtonLoadsResultActivity() {
        // register next activity that needs to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(ResultsActivity.class.getName(), null, false);
        // Enter an answer
        enterAnswer("t");
        // Tap Submit button
        TouchUtils.clickView(this, m_btnSubmit);
        ResultsActivity nextActivity = (ResultsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Result screen activity is not launched", nextActivity);
        nextActivity .finish();
    }

    public void testLetterCountIntentExtraIsReceived() {
        int intentExtra = m_gameScreenActivity.getIntent().getIntExtra(LETTER_COUNT, 0);
        assertEquals(5, intentExtra);
    }

    public void testFiveTextViewsHaveLettersWhenIntentExtraIs5() {
        assertTrue("First textview does not have letter", m_tvFirstLetter.getText().length() == 1);
        assertTrue("Second textview does not have letter", m_tvSecondLetter.getText().length() == 1);
        assertTrue("Third textview does not have letter", m_tvThirdLetter.getText().length() == 1);
        assertTrue("Fourth textview does not have letter", m_tvFourthLetter.getText().length() == 1);
        assertTrue("Fifth textview does not have letter", m_tvFifthLetter.getText().length() == 1);
    }

    public void testSixthTextViewDoesNotExistWhenIntentExtraIs5() {
        assertNull("Sixth textview exists", m_tvSixthLetter);
    }

    public void testSixthTextViewIsDisplayedWhenIntentExtraIs6() {
        m_gameScreenActivity.finish();  // close the activity
        setActivity(null); // forces next call of getActivity to re-open the activity
        Intent intent = new Intent();
        intent.putExtra(LETTER_COUNT, 6);
        setActivityIntent(intent);
        m_gameScreenActivity = getActivity();
        m_tvSixthLetter = (TextView) m_gameScreenActivity.findViewById(R.id.tvSixthLetter);
        assertNotNull("Sixth textview does not exist", m_tvSixthLetter);
    }

    public void testSixTextViewsHaveLettersWhenIntentExtraIs6() {
        m_gameScreenActivity.finish();  // close the activity
        setActivity(null); // forces next call of getActivity to re-open the activity
        Intent intent = new Intent();
        intent.putExtra(LETTER_COUNT, 6);
        setActivityIntent(intent);
        m_gameScreenActivity = getActivity();
        m_tvSixthLetter = (TextView) m_gameScreenActivity.findViewById(R.id.tvSixthLetter);
        assertTrue("First textview does not have letter", m_tvFirstLetter.getText().length() == 1);
        assertTrue("Second textview does not have letter", m_tvSecondLetter.getText().length() == 1);
        assertTrue("Third textview does not have letter", m_tvThirdLetter.getText().length() == 1);
        assertTrue("Fourth textview does not have letter", m_tvFourthLetter.getText().length() == 1);
        assertTrue("Fifth textview does not have letter", m_tvFifthLetter.getText().length() == 1);
        assertTrue("Sixth textview does not have letter", m_tvSixthLetter.getText().length() == 1);
    }
}
