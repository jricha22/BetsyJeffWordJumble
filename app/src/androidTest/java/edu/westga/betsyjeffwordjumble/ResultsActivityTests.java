package edu.westga.betsyjeffwordjumble;

import android.test.ActivityInstrumentationTestCase2;
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
}
