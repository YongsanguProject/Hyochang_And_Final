package com.smtown.yongsangu_ar_project;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

import com.smtown.bgm.Game_Main_Bgm;
import com.smtown.bgm.Hyochang_Ending_BGM;
import com.smtown.yongsangu_ar_project.hyochang.ending.camera.H_CameraTestActivity;
import com.smtown.yongsangu_ar_project.hyochang.ending.reward.H_RewardInputActivity;
import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.CameraTestActivity;
import com.smtown.yongsangu_ar_project.sookmyung.ending.reward.RewardInputActivity;
import com.unity3d.player.*;


public class UnityPlayerActivity extends Activity
{
    public static UnityPlayerActivity activity = null;    //액티비티 변수 선언

    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    String msg;

    // Setup activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        activity = this;
        //게임 갈래 구분하는 곳///////
        Intent intent = getIntent();

        msg = intent.getStringExtra("scene");
        ////////////////////////


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        mUnityPlayer = new UnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();
    }

    public void GetSceneName(){ //숙대맵인지, 효창맵인지 구분하는 함수

            UnityPlayer.UnitySendMessage("AndroidManager","ChangeScene",msg);

    }

    public static void CallActivity(Activity activity){ //숙대맵 - 사진찍기
        Intent intent = new Intent(activity,CameraTestActivity.class);
        activity.startActivity(intent);
        //activity.finish();
    }
    public static void CallActivity2(Activity activity){//숙대맵 - 표창창부분 정보 입력하기
        Intent intent = new Intent(activity,RewardInputActivity.class);
        activity.startActivity(intent);
        //activity.finish();

    }
    public static void CallActivity3(Activity activity){ //효창맵 - 사진찍기
        Intent intent = new Intent(activity,H_CameraTestActivity.class);
        activity.startActivity(intent);
    }
    public static void CallActivity4(Activity activity){//효창맵 - 표창창부분 정보 입력하기
        Intent intent = new Intent(activity,H_RewardInputActivity.class);
        activity.startActivity(intent);
    }
    public static void CallActivity5(Activity activity){//효창맵 - 게임종료버튼 눌렀을때 메뉴선택화면으로 돌아감
      // Intent intent = new Intent(activity,MainActivity.class);
       //activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override
    protected void onDestroy ()
    {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override
    protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mUnityPlayer.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
    @Override
    public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
    /*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }
}
