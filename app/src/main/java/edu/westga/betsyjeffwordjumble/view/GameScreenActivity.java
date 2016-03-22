package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.controller.Controller;

public class GameScreenActivity extends AppCompatActivity {

    public final static String RESULT = "edu.westga.betsyjeffwordjumble.RESULT";

    private Button m_btnEnter;
    private EditText m_etAnswer;
    private TextView m_tvScrambledWord;
    private Controller m_controller;
    private String m_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        m_tvScrambledWord = (TextView)findViewById(R.id.tvScrambledWord);
        m_controller = new Controller();

        m_btnEnter = (Button)findViewById(R.id.btnEnter);
        m_btnEnter.setEnabled(false);

        m_etAnswer = (EditText)findViewById(R.id.etAnswer);
        m_etAnswer.addTextChangedListener(this.watcher);

        Intent intent = getIntent();
        int letterCount = intent.getIntExtra(MainActivity.LETTER_COUNT, 0);
        if (letterCount == 5) {
            m_tvScrambledWord.setText(m_controller.getAFiveCharWord());
        }
        else if (letterCount == 6) {
            m_tvScrambledWord.setText(m_controller.getASixCharWord());
        }

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
            boolean answerNotEmpty = GameScreenActivity.this.m_etAnswer.getText().toString().trim().length()>0;
            GameScreenActivity.this.m_btnEnter.setEnabled(answerNotEmpty);
        }
    };

    /** Called when the user clicks the Enter button from Game screen*/
    public void showResult(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        String theGuess = m_etAnswer.getText().toString();
        if (m_controller.checkResult(theGuess)) {
            m_result = "Awesome!";
        } else {
            m_result = "Sorry!";
        }
        intent.putExtra(RESULT, m_result);
        startActivity(intent);
    }

    /** Show a hint*/
    public void showHint(View view) {
        String hint = m_controller.getAHint();
        Toast toast = Toast.makeText(getApplicationContext(), hint, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 300);
        toast.show();
    }

}
