package com.example.teamcloudvarsity.varsity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;



public class CourseVideo_1 extends Activity {
    private final String Videos_URL = "android.resource://" + R.raw.course_video_1;

    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the layout from video_main.xml
        setContentView(R.layout.activity_course_video_1);

        if (mediaControls == null) {
            mediaControls = new MediaController(this);
        }

        // Find your VideoView in your video_main.xml layout
        myVideoView = (VideoView) findViewById(R.id.course_video);

        // Create a progressbar
        progressDialog = new ProgressDialog(this);
        // Set progressbar title
        progressDialog.setTitle("ABCDEFGH");
        // Set progressbar message
        progressDialog.setMessage("Loading...");

        progressDialog.setCancelable(false);
        // Show progressbar
        progressDialog.show();

        try {
            Uri video = Uri.parse(Videos_URL);
            myVideoView.setVideoURI(video);
            myVideoView.setMediaController(mediaControls);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }
}
