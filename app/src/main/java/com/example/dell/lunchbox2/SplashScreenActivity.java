package com.example.dell.lunchbox2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView logoimageview;
    TextView titletextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logoimageview=findViewById(R.id.logoimageview);
        titletextview=findViewById(R.id.titletextview);

        Animation anim1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        logoimageview.startAnimation(anim1);
        titletextview.startAnimation(anim1);
        Thread newthread = new Thread(){
            public void run() {
                try {
                    sleep(5000);
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                    super.run();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        newthread.start();
    }

}
