package edu.westga.betsyjeffwordjumble.viewTests;

import android.app.Instrumentation;
import android.app.SearchManager;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.view.GameScreenActivity;
import edu.westga.betsyjeffwordjumble.view.MainActivity;
import edu.westga.betsyjeffwordjumble.view.ResultsActivity;

/**
 * Created by Betsy on 3/18/2016.
 */
public class ResultsActivityTests extends ActivityInstrumentationTestCase2<ResultsActivity> {
    public final static String RESULT = "edu.westga.betsyjeffwordjumble.RESULT";

    ResultsActivity m_resultsActivity;
    Button m_btnReplay;
    TextView m_tvResult;

    public ResultsActivityTests() {
        super(ResultsActivity.class);
    }

    @Override

    protected void setUp() {
        Intent intent = new Intent();
        //intent.setClassName(getInstrumentation().getTargetContext(), GameScreenActivity.class.getName());
        intent.putExtra(RESULT, "Correct!");
        setActivityIntent(intent);
        m_resultsActivity = getActivity();
        m_btnReplay = (Button) m_resultsActivity.findViewById(R.id.btnReplay);
        m_tvResult = (TextView)m_resultsActivity.findViewById(R.id.tvResult);
    }

    public void testActivityExists() {
        assertNotNull(m_resultsActivity);
    }

    public void testButtonIsEnabled() {
        assertTrue(m_btnReplay.isEnabled());
    }

    public void testReplayButtonLoadsMainActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(MainActivity.class.getName(), null, false);
        // Tap replay button
        TouchUtils.clickView(this, m_btnReplay);
        MainActivity nextActivity = (MainActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Main activity is not launched", nextActivity);
        nextActivity .finish();
    }

    public void testIntentExtraIsReceived() {
        String intentExtra = "";
        intentExtra = m_resultsActivity.getIntent().getStringExtra(RESULT);
        assertEquals("Correct!", intentExtra);
        assertNotNull("Intent extra not received", m_tvResult.getText());
    }

    public void testResultIsDisplayedInTextView() {
        assertTrue("Result is not displayed", m_tvResult.getText().toString().trim().length() != 0);
    }
}
