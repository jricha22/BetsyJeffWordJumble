package edu.westga.betsyjeffwordjumble;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

/**
 * Created by Betsy on 3/18/2016.
 */
public class ResultsActivityTests extends ActivityInstrumentationTestCase2<ResultsActivity> {
    ResultsActivity mResultsActivity;
    Button mBtnReplay;

    public ResultsActivityTests() {
        super(ResultsActivity.class);
    }

    @Override
    protected void setUp() {
        mResultsActivity = getActivity();
        mBtnReplay = (Button) mResultsActivity.findViewById(R.id.btnReplay);
    }

    public void testActivityExists() {
        assertNotNull(mResultsActivity);
    }

    public void testButtonIsEnabled() {
        assertTrue(mBtnReplay.isEnabled());
    }

    public void testReplayButtonLoadsMainActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(MainActivity.class.getName(), null, false);
        // Tap replay button
        TouchUtils.clickView(this, mBtnReplay);
        MainActivity nextActivity = (MainActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Main activity is not launched", nextActivity);
        nextActivity .finish();
    }
}
