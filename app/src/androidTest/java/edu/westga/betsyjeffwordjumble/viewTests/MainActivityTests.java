package edu.westga.betsyjeffwordjumble.viewTests;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.view.GameScreenActivity;
import edu.westga.betsyjeffwordjumble.view.MainActivity;

/**
 * Created by Betsy on 3/18/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity m_activity;
    Button m_btnPlay;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() {
        m_activity = getActivity();
        m_btnPlay = (Button) m_activity.findViewById(R.id.btnPlay);
    }

    public void testActivityExists() {
        assertNotNull(m_activity);
    }

    public void testButtonIsEnabled() {
        assertTrue(m_btnPlay.isEnabled());
    }

    public void testPlayButtonLoadsGameScreenActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(GameScreenActivity.class.getName(), null, false);
        // Tap play button
        TouchUtils.clickView(this, m_btnPlay);
        GameScreenActivity nextActivity = (GameScreenActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Game screen activity is not launched", nextActivity);
        nextActivity .finish();
    }
}
