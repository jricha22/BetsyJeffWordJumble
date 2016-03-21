package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.controller.Controller;

public class GameScreenActivity extends AppCompatActivity {

    public final static String RESULT = "edu.westga.betsyjeffwordjumble.RESULT";

    private Button mBtnEnter;
    private EditText mEtAnswer;
    private TextView mTvScrambledWord;
    private Controller mController;
    boolean mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        mBtnEnter = (Button)findViewById(R.id.btnEnter);
        mBtnEnter.setEnabled(false);

        mEtAnswer = (EditText)findViewById(R.id.etAnswer);
        mEtAnswer.addTextChangedListener(this.watcher);

        mTvScrambledWord = (TextView)findViewById(R.id.tvScrambledWord);
        mController  = new Controller();
        mTvScrambledWord.setText(mController.getAWord());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Listens for any changes in the EditText box and enables/disables the button appropriately
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean answerNotEmpty = GameScreenActivity.this.mEtAnswer.getText().toString().trim().length()>0;
            GameScreenActivity.this.mBtnEnter.setEnabled(answerNotEmpty);
        }
    };

    /** Called when the user clicks the Enter button from Game screen*/
    public void showResult(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        String theGuess = mEtAnswer.getText().toString();
        mResult = mController.checkResult(theGuess);
        intent.putExtra(RESULT, mResult);
        startActivity(intent);
    }

}
