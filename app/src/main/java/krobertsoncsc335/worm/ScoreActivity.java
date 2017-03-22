package krobertsoncsc335.worm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView txtScoreNumView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Bundle extras = getIntent().getExtras();

        int finalScore = extras.getInt("finalScore");

        txtScoreNumView = (TextView)findViewById(R.id.txtScoreNumView);
        txtScoreNumView.setText(String.valueOf(finalScore));
    }

    //RESTART button's onClick property
    public void restartGame(){
        Intent intent= new Intent(ScoreActivity.this,GameActivity.class);
        startActivity(intent);
        finish();
    }


}
