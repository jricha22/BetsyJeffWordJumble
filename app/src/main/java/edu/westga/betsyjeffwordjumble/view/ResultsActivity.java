package edu.westga.betsyjeffwordjumble.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.westga.betsyjeffwordjumble.R;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Intent intent = getIntent();
        String result = intent.getStringExtra(GameScreenActivity.RESULT);
        if (result.equals("Awesome!")) {
            imageView.setImageResource(R.drawable.thumbsup);
        } else if (result.equals("Sorry!")) {
            imageView.setImageResource(R.drawable.disappointed);
        }
        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText(result);
    }

    /** Called when the user clicks the Replay button from Result screen*/
    public void reloadGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
