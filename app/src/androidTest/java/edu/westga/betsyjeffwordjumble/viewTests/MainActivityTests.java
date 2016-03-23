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
    private Button m_btnPlay;
    private RadioGroup m_radioGroup;
    private RadioButton m_rb_five_letter_word;
    private RadioButton m_rb_six_letter_word;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() {
        m_activity = getActivity();
        m_btnPlay = (Button) m_activity.findViewById(R.id.btnPlay);
        m_radioGroup = (RadioGroup) m_activity.findViewById(R.id.radioGroup);
        m_rb_five_letter_word = (RadioButton) m_activity.findViewById(R.id.five_letters);
        m_rb_six_letter_word = (RadioButton) m_activity.findViewById(R.id.six_letters);
    }

    public void testActivityExists() {
        assertNotNull(m_activity);
    }

    public void testPlayButtonIsDisabled() {
        m_radioGroup.clearCheck();
        assertFalse("Play blue_button is not disabled.", m_btnPlay.isEnabled());
    }

    public void selectRadioButton1() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                //MainActivityTests.this.nameEditText.requestFocus();
                MainActivityTests.this.m_rb_five_letter_word.setChecked(true);
            }
        });
    }

    public void selectRadioButton2 () {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                //MainActivityTests.this.nameEditText.requestFocus();
                MainActivityTests.this.m_rb_six_letter_word.setChecked(true);
            }
        });
    }

    public void testPlayButtonIsEnabledWhenFirstRadioButtonIsSelected() {

        selectRadioButton1();
        assertTrue("Play blue_button is not enabled.", m_btnPlay.isEnabled());
    }

    public void testPlayButtonIsEnabledWhenSecondRadioButtonIsSelected() {
        selectRadioButton2();
        assertTrue("Play blue_button is not enabled.", m_btnPlay.isEnabled());
    }

    public void testPlayButtonLoadsGameScreenActivity() {
        // register next activity that need to be monitored.
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation()
                .addMonitor(GameScreenActivity.class.getName(), null, false);
        selectRadioButton1();
        // Tap play blue_button
        TouchUtils.clickView(this, m_btnPlay);
        GameScreenActivity nextActivity = (GameScreenActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
        // next activity is opened and captured.
        assertNotNull("Game screen activity is not launched", nextActivity);
        nextActivity .finish();
    }
}
