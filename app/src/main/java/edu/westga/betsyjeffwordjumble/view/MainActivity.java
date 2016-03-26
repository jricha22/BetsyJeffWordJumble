package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import edu.westga.betsyjeffwordjumble.R;

public class MainActivity extends AppCompatActivity {

    public final static String LETTER_COUNT = "edu.westga.betsyjeffwordjumble.LETTER_COUNT";

    private int m_letterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    /** Called when the user clicks the five letter challenge button */
    public void playFiveLetterGame(View view) {
        m_letterCount = 5;
        playGame();
    }

    /** Called when the user clicks the six letter challenge button */
    public void playSixLetterGame(View view) {
        m_letterCount = 6;
        playGame();
    }

    public void playGame(){
        Intent intent = new Intent(this, GameScreenActivity.class);
        intent.putExtra(LETTER_COUNT, m_letterCount);
        startActivity(intent);
    }
}
