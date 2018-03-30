package com.timothy.optifind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logoview;
    TextView logoText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoview = (ImageView) findViewById(R.id.logoimage);
        logoText = (TextView) findViewById(R.id.textView);

        Animation newTrans = AnimationUtils.loadAnimation(this,R.anim.logoanim);
        logoview.setAnimation(newTrans);

        Animation anotherTrans = AnimationUtils.loadAnimation(this,R.anim.splashanim);
        logoText.setAnimation(anotherTrans);

        final Intent i = new Intent(this, SignUp.class);
        Thread timer = new Thread (){
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
