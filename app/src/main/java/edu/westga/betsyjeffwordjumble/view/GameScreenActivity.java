package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import edu.westga.betsyjeffwordjumble.R;
import edu.westga.betsyjeffwordjumble.controller.Controller;

public class GameScreenActivity extends AppCompatActivity {

    public final static String RESULT = "edu.westga.betsyjeffwordjumble.RESULT";

    private Button m_btnSubmit;
    private EditText m_etAnswer;
    private Controller m_controller;
    private String m_result;
    private TextView m_tvTitle;
    private TextView m_tvFirstLetter;
    private TextView m_tvSecondLetter;
    private TextView m_tvThirdLetter;
    private TextView m_tvFourthLetter;
    private TextView m_tvFifthLetter;
    private TextView m_tvSixthLetter;
    private String m_theWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        m_controller = new Controller();
        m_tvTitle = (TextView)findViewById(R.id.tvTitle);

        m_btnSubmit = (Button)findViewById(R.id.btnSubmit);
        m_btnSubmit.setEnabled(false);
        m_btnSubmit.setAlpha(.5f);

        m_etAnswer = (EditText)findViewById(R.id.etAnswer);
        m_etAnswer.addTextChangedListener(this.watcher);

        m_tvFirstLetter = (TextView)findViewById(R.id.tvFirstLetter);
        m_tvSecondLetter = (TextView)findViewById(R.id.tvSecondLetter);
        m_tvThirdLetter = (TextView)findViewById(R.id.tvThirdLetter);
        m_tvFourthLetter = (TextView)findViewById(R.id.tvFourthLetter);
        m_tvFifthLetter = (TextView)findViewById(R.id.tvFifthLetter);
        m_tvSixthLetter = null;

        Intent intent = getIntent();
        int letterCount = intent.getIntExtra(MainActivity.LETTER_COUNT, 0);

        if (letterCount == 5) {
            m_tvTitle.setText("Five Letter Challenge");
            m_theWord = m_controller.getAFiveCharWord();
            setLettersInTextViews();
        }
        else if (letterCount == 6) {
            m_tvTitle.setText("Six Letter Challenge");
            m_theWord = m_controller.getASixCharWord();
            addSixthLetterTextView();
            setLettersInTextViews();
        }

    }

    private void addSixthLetterTextView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llLetters);
        m_tvSixthLetter = new TextView(this);
        m_tvSixthLetter.setId(R.id.tvSixthLetter);
        m_tvSixthLetter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        m_tvSixthLetter.setGravity(Gravity.CENTER);
        m_tvSixthLetter.setTextColor(Color.WHITE);
        m_tvSixthLetter.setTextSize(30);
        m_tvSixthLetter.setBackgroundResource(R.drawable.colored_box);
        linearLayout.addView(m_tvSixthLetter);
    }

    private void setLettersInTextViews() {
        m_tvFirstLetter.setText(m_theWord.substring(0, 1));
        m_tvSecondLetter.setText(m_theWord.substring(1, 2));
        m_tvThirdLetter.setText(m_theWord.substring(2, 3));
        m_tvFourthLetter.setText(m_theWord.substring(3, 4));
        m_tvFifthLetter.setText(m_theWord.substring(4, 5));
        if (m_tvSixthLetter != null) {
            m_tvSixthLetter.setText(m_theWord.substring(5, 6));
        }
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
            GameScreenActivity.this.m_btnSubmit.setEnabled(answerNotEmpty);
            if (answerNotEmpty) {
                GameScreenActivity.this.m_btnSubmit.setAlpha(1f);
            } else {
                GameScreenActivity.this.m_btnSubmit.setAlpha(.5f);
            }
        }
    };

    /** Called when the user clicks the Submit button from Game screen*/
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
        toast.setGravity(Gravity.BOTTOM, 0, 300);
        toast.show();
    }

}
