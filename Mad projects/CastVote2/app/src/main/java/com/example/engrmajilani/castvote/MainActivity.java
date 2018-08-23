package com.example.engrmajilani.castvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtsplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtsplash = (TextView)findViewById(R.id.txtsplash);

        Animation animsplash= AnimationUtils.loadAnimation(this, R.anim.splash_action);
        txtsplash.setAnimation(animsplash);

        final Intent i = new Intent(MainActivity.this,login.class);

        Thread thrd = new Thread(){
            @Override
            public void run() {
                super.run();

                try{
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
            thrd.start();

    }
}
