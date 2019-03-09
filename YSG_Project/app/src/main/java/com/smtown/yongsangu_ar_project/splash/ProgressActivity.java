package com.smtown.yongsangu_ar_project.splash;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.smtown.bgm.Game_Main_Bgm;
import com.smtown.yongsangu_ar_project.MainActivity;
import com.smtown.yongsangu_ar_project.R;
import com.smtown.yongsangu_ar_project.UnityPlayerActivity;

public class ProgressActivity extends AppCompatActivity {
    private static int progress_percent;
    public String msg;
  //  public boolean flag;
  //  public boolean flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
      //  flag = false;
      //  flag2 = false;
        //bgm
        startService(new Intent(ProgressActivity.this,Game_Main_Bgm.class));
        //
        progress_percent = 0;

        final Intent intent = new Intent(ProgressActivity.this, WarningActivity.class);
//        final Intent intent = new Intent(ProgressActivity.this, UnityPlayerActivity.class);
       // msg = "FindPark";
        //intent.putExtra("scene",msg);
        intent.putExtra("start",1);

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            progress_percent += 15;
                            Thread.sleep(1000);
                            ProgressBar progress = findViewById(R.id.main_progressBar);
                            progress.setProgress(progress_percent);

                            if (progress_percent >= 100) {
                               // flag2 = true;
                                startActivity(intent);
                                finish();
                                currentThread().interrupt();
                            }
                        }
                    } catch (Throwable t) {

                    } finally {

                    }
                }
            }
        }.start();
    }

//    @Override
//    protected void onUserLeaveHint() {
//        super.onUserLeaveHint();
//        flag = true;
//        Log.e("flag","여기로옴");
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(flag == true && flag2 == false){
//            stopService(new Intent(ProgressActivity.this,Game_Main_Bgm.class));
//            flag = false;
//
//        }else if(flag == true && flag2 == true){
//
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(flag == false && flag2==false){
//            startService(new Intent(ProgressActivity.this,Game_Main_Bgm.class));
//        }
//    }

}
