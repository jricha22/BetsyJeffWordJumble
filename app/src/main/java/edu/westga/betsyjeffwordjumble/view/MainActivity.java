package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.westga.betsyjeffwordjumble.R;

public class MainActivity extends AppCompatActivity {

    public final static String LETTER_COUNT = "edu.westga.betsyjeffwordjumble.LETTER_COUNT";

    private RadioGroup m_radioGroup;
    private Button m_btnPlay;
    private RadioButton m_checkedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_btnPlay = (Button)findViewById(R.id.btnPlay);
        m_btnPlay.setEnabled(false);
        m_radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        m_radioGroup.clearCheck();
        m_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (m_radioGroup.getCheckedRadioButtonId() == -1) {
                    m_btnPlay.setEnabled(false);
                } else {
                    m_btnPlay.setEnabled(true);
                    m_checkedRadioButton = (RadioButton) m_radioGroup.findViewById(m_radioGroup.getCheckedRadioButtonId());
                }
            }
        });

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void playGame(View view) {
        Intent intent = new Intent(this, GameScreenActivity.class);
        int checkedIndex = m_radioGroup.indexOfChild(m_checkedRadioButton);
        int numberOfLetters = 0;
        switch (checkedIndex) {
            case 0:
                numberOfLetters = 5;
                break;
            case 1:
                numberOfLetters = 6;
                break;
            default:
                break;
        }
        intent.putExtra(LETTER_COUNT, numberOfLetters);
        startActivity(intent);
    }
}
