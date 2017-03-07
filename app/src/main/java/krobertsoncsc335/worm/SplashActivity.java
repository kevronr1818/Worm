package krobertsoncsc335.worm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        DisplayAdvisor.setScreenDimmensions(metrics);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                } finally {
                    Intent openMainActivity = new Intent("krobertsoncsc335.GAMEACTIVITY");
                    startActivity(openMainActivity);
                }
            }
        };

        timer.start();
    }
}