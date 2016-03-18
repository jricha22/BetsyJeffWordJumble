package edu.westga.betsyjeffwordjumble;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Betsy on 3/18/2016.
 */
public class GameScreenActivityTests extends ActivityInstrumentationTestCase2<GameScreenActivity> {
    private GameScreenActivity mGameScreenActivity;
    private Button mBtnEnter;
    private EditText mEtAnswer;

    public GameScreenActivityTests() {
        super(GameScreenActivity.class);
    }

    @Override
    protected void setUp() {
        mGameScreenActivity = getActivity();
        mBtnEnter = (Button)mGameScreenActivity.findViewById(R.id.btnEnter);
        mEtAnswer = (EditText)mGameScreenActivity.findViewById(R.id.etAnswer);
    }

    public void testActivityExists() {
        assertNotNull(mGameScreenActivity);
    }

    public void testEnterButtonIsDisabled() {
        assertFalse(mBtnEnter.isEnabled());
    }

    private void enterAnswer(String answerText) {
        final String text = answerText;
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                GameScreenActivityTests.this.mEtAnswer.setText(text);
            }
        });
    }

    public void testEnterButtonIsDisabledWhenEmptyStringIsEntered() {
        enterAnswer(" ");
        assertFalse(mBtnEnter.isEnabled());
    }

    private void testEnterButtonIsEnabledWhenNonEmptyStringIsEntered() {
        enterAnswer("t");
        assertTrue(mBtnEnter.isEnabled());
    }

    public void testEnterButtonLoadsResultActivity() {
        // register next activity that needs to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(ResultsActivity.class.getName(), null, false);
        // Enter an answer
        enterAnswer("t");
        // Tap enter button
        TouchUtils.clickView(this, mBtnEnter);
        ResultsActivity nextActivity = (ResultsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Result screen activity is not launched", nextActivity);
        nextActivity .finish();
    }

}
