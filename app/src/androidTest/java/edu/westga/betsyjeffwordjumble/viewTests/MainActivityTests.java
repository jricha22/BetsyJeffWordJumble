package edu.westga.betsyjeffwordjumble.viewTests;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.view.GameScreenActivity;
import edu.westga.betsyjeffwordjumble.view.MainActivity;

/**
 * Created by Betsy on 3/18/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity m_activity;
    private Button m_btnFiveLetter;
    private Button m_btnSixLetter;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() {
        m_activity = getActivity();
        m_btnFiveLetter = (Button) m_activity.findViewById(R.id.btnFiveLetterGame);
        m_btnSixLetter = (Button) m_activity.findViewById(R.id.btnSixLetterGame);
    }

    public void testActivityExists() {
        assertNotNull(m_activity);
    }

    public void testFiveLetterButtonIsEnabled() {
        assertTrue("Five letter game button is not enabled.", m_btnFiveLetter.isEnabled());
    }

    public void testSixLetterButtonIsEnabled() {
        assertTrue("Six letter game button is not enabled.", m_btnSixLetter.isEnabled());
    }

    public void testFiveLetterButtonLoadsGameScreenActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(GameScreenActivity.class.getName(), null, false);
        // Tap five letter button
        TouchUtils.clickView(this, m_btnFiveLetter);
        GameScreenActivity nextActivity = (GameScreenActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Game screen activity is not launched", nextActivity);
        nextActivity .finish();
    }

    public void testSixLetterButtonLoadsGameScreenActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(GameScreenActivity.class.getName(), null, false);
        // Tap six letter button
        TouchUtils.clickView(this, m_btnSixLetter);
        GameScreenActivity nextActivity = (GameScreenActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Game screen activity is not launched", nextActivity);
        nextActivity .finish();
    }
}
