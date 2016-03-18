package edu.westga.betsyjeffwordjumble;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by Betsy on 3/18/2016.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mActivity;
    Button btnPlay;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() {
        mActivity = getActivity();
        btnPlay = (Button)mActivity.findViewById(R.id.btnPlay);
    }

    public void testActivityExists() {
        assertNotNull(mActivity);
    }

    public void testButtonIsEnabled() {
        assertTrue(btnPlay.isEnabled());
    }
}
