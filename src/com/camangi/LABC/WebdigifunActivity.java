package com.camangi.LABC;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class WebdigifunActivity extends Activity {
	MyVideoView videoView;
	MyMediaController2 mediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
        videoView = (MyVideoView)findViewById(R.id.VideoView);
        
        mediaController = new MyMediaController2(this);
        //mediaController.setEnabled(true);
        mediaController.setAnchorView(videoView);
        

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.story)); 

        videoView.setMediaController(mediaController);
        //videoView.requestFocus();

        videoView.start(); 
		
    }
    
    @Override
    protected void onPause() {  //按下退出鍵 系統預設呼叫 onPause

	     videoView.pause();
	     finish();
	     super.onDestroy(); //這行以防系統以為我亂呼叫
    }


    @Override
    protected void onStop() {
		 super.onStop();
		 super.onDestroy();
		 finish();
    }


    @Override
    protected void onDestroy(){ //真正作用區
        super.onDestroy();
        
        //Kill myself
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

/*

public class WebdigifunActivity extends Activity implements OnErrorListener, OnCompletionListener {
    public static final String TAG = "VideoPlayer";
    private VideoView mVideoView;
    private Uri mUri;
    private int mPositionWhenPaused = -1;

    private MyMediaController mMediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        //Set the screen to landscape.
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mVideoView = (VideoView)findViewById(R.id.VideoView);

        //Video file
        mUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.story);

        mMediaController = new MyMediaController(this);
        mVideoView.setMediaController(mMediaController);
    }

    public void onStart() {
        // Play Video
        mVideoView.setVideoURI(mUri);
        mVideoView.start();
        Log.d("VIDEOTEST", "onStart");
        super.onStart();
    }

    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();
        Log.d("VIDEOTEST", "onPause");

        super.onPause();
    }

    public void onResume() {
        // Resume video player
        if(mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }
        Log.d("VIDEOTEST", "onResume");
        super.onResume();
    }

    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.d("VIDEOTEST", "OnCompletionListener");
        this.finish();
	}
}
*/