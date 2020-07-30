package com.fypic.imageclassification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.fypic.imageclassification.ActLActivity.getLoginStatus;

public class FadeActivity2 extends AppCompatActivity {

    Button camBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade2);

        camBtn = findViewById(R.id.ContButton);

        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(FadeActivity2.this, MainActivity.class);
                cameraIntent.putExtra("camSwitch", 1);
                startActivity(cameraIntent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!getLoginStatus()){
            Intent unloggedIntent = new Intent(FadeActivity2.this, MainActivity.class);
            startActivity(unloggedIntent);
        }

        else {
            Intent loggedIntent = new Intent(FadeActivity2.this, MainActivityLogged.class);
            startActivity(loggedIntent);
        }
    }
}
