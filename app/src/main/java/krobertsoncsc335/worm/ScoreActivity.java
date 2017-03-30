package krobertsoncsc335.worm;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView txtScoreNumView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Bundle extras = getIntent().getExtras();

        int finalScore = extras.getInt("finalScore");

        txtScoreNumView = (TextView) findViewById(R.id.txtScoreNumView);
        txtScoreNumView.setText(String.valueOf(finalScore));
    }

    //RESTART button's onClick property
    public void restartGame(View view) {
        try {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }




    }
}
