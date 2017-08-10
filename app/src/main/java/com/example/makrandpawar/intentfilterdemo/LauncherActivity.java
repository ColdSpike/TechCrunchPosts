package com.example.makrandpawar.intentfilterdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if (action.equals(Intent.ACTION_VIEW) && data!=null){
            String[] splitUrl = data.toString().split("/");
            Intent detailIntent = new Intent(this,DetailedPost.class);
            try {
                detailIntent.putExtra("SLUG", splitUrl[6]);
                startActivity(detailIntent);
                finish();
            }catch (ArrayIndexOutOfBoundsException e){
                startActivity(new Intent(LauncherActivity.this,MainActivity.class));
                finish();
            }
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(LauncherActivity.this,MainActivity.class));
                    finish();
                }
            },2000);
        }
    }
}
