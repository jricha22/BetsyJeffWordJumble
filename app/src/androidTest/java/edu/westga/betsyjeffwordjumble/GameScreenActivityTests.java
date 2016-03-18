package edu.westga.betsyjeffwordjumble;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by Betsy on 3/18/2016.
 */
public class GameScreenActivityTests extends ActivityInstrumentationTestCase2<GameScreenActivity> {
    private GameScreenActivity mGameScreenActivity;
    private Button mBtnEnter;

    public GameScreenActivityTests() {
        super(GameScreenActivity.class);
    }

    @Override
    protected void setUp() {
        mGameScreenActivity = getActivity();
        mBtnEnter = (Button)mGameScreenActivity.findViewById(R.id.btnEnter);
    }

    public void testActivityExists() {
        assertNotNull(mGameScreenActivity);
    }

    public void testEnterButtonIsDisabled() {
        assertFalse(mBtnEnter.isEnabled());
    }

}
