package com.ayushi.vidrec;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnRecVideo;

    VideoView imgViewVideo;

    private static final int VIDEO_CAPTURE=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgViewVideo=(VideoView)this.findViewById(R.id.imgViewVideo);
        btnRecVideo=(Button)this.findViewById(R.id.btnRecVideo);

        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        btnRecVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)throws FileUriExposedException {
                Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,VIDEO_CAPTURE);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURE && resultCode == RESULT_OK) {
            imgViewVideo.setVisibility(View.VISIBLE);
            assert imgViewVideo != null;
            Uri videoUri = data.getData();
            imgViewVideo.setVideoURI(videoUri);
            imgViewVideo.setMediaController(new MediaController(getApplicationContext()));
            imgViewVideo.requestFocus();
            imgViewVideo.start();
        }
    }
}