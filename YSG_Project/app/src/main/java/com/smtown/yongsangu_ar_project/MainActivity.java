package com.smtown.yongsangu_ar_project;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smtown.bgm.Game_Main_Bgm;
import com.smtown.bgm.Hyochang_Ending_BGM;
import com.smtown.bgm.Sookmyung_Main_Bgm;
import com.smtown.yongsangu_ar_project.hyochang.ending.camera.H_CameraTestActivity;
import com.smtown.yongsangu_ar_project.splash.SplashActivity;

public class MainActivity extends AppCompatActivity{

    ImageView map_sook;
   ImageView map_hyo;
    public String msg;
    public int start;//처음인지 확인

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (UnityPlayerActivity.activity != null) { //액티비티가 살아 있다면 (여기서 유니티액티비티 초기화)

            Activity activity = UnityPlayerActivity.activity;
            activity.finish();
        }

        map_sook = findViewById(R.id.map_sook);
        map_hyo = findViewById(R.id.map_hyo);

        Intent intent_r = getIntent();
        start = intent_r.getIntExtra("start",0);






        map_sook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "숙대맵은 아직 서비스 중입니다:)", Toast.LENGTH_SHORT).show();
                //진짜 부분


                Intent intent1 = new Intent(MainActivity.this, com.smtown.yongsangu_ar_project.UnityPlayerActivity.class);
                msg = "SookmyungIntro";
//                msg = "FindPark"; //테스트용
                intent1.putExtra("scene",msg);
                startActivity(intent1);
            }
        });

        map_hyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //Intent intent1 = new Intent(MainActivity.this, FirstIntroActivity.class);
                //startActivity(intent1);


               // 진짜
                Intent intent2 = new Intent(MainActivity.this, com.smtown.yongsangu_ar_project.UnityPlayerActivity.class);
                msg = "HyochangIntro";
                intent2.putExtra("scene",msg);
                startActivity(intent2);

                //Intent intent2 = new Intent(MainActivity.this, H_CameraTestActivity.class);
                //startActivity(intent2);
            }
        });



    }

    @Override
    protected void onPause() { //맵 선택했을때 / 홈 키 눌렀을때
        super.onPause();
        stopService(new Intent(MainActivity.this,Game_Main_Bgm.class));


    }

    @Override
    protected void onResume() { //다시 돌아올때
        super.onResume();
        if(start == 1){ //처음인지 확인
            start = 0;
        }else{
            startService(new Intent(MainActivity.this,Game_Main_Bgm.class));
            stopService(new Intent(MainActivity.this,Hyochang_Ending_BGM.class));
            stopService(new Intent(MainActivity.this,Sookmyung_Main_Bgm.class));
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        stopService(new Intent(MainActivity.this,Game_Main_Bgm.class));
//        stopService(new Intent(MainActivity.this,Hyochang_Ending_BGM.class));
//        stopService(new Intent(MainActivity.this,Sookmyung_Main_Bgm.class));
//
//    }
}
