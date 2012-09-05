package com.steke.webdigifun;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

public class WebdigifunActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
        MyVideoView videoView = (MyVideoView)findViewById(R.id.VideoView);
        
        MyMediaController2 mediaController = new MyMediaController2(this);
        //mediaController.setEnabled(true);
        mediaController.setAnchorView(videoView);
        

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.story)); 

        videoView.setMediaController(mediaController);
        //videoView.requestFocus();

        videoView.start(); 
		
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