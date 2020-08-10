package com.fypic.imageclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FadeActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    private static int WELCOME_TIMEOUT=10000;
    TextView timeLeft;
    Button skipBtn;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade);
        timeLeft = findViewById(R.id.text_view_countdown);
        skipBtn = findViewById(R.id.buttonSkip);

        countDownTimer = new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft.setText(millisUntilFinished/1000 + " seconds left");
            }

            @Override
            public void onFinish() {

            }
        };


        countDownTimer.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome=new Intent(FadeActivity.this, FadeActivity2.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        }, WELCOME_TIMEOUT);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                stopHandler();
                Intent welcome=new Intent(FadeActivity.this, FadeActivity2.class);
                startActivity(welcome);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });

    }

    public void stopHandler() {
        handler.removeMessages(0);
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        stopHandler();
    }

}
