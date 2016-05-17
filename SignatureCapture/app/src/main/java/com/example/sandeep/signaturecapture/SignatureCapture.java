package com.example.sandeep.signaturecapture;

import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SignatureCapture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_capture);
        GestureOverlayView sign = (GestureOverlayView) findViewById(R.id.gestureOverlayView);
        sign.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                try {
                    String path= Environment.getExternalStorageDirectory().toString();
                    File file=new File(path,"sign.png");
                    OutputStream fout=new FileOutputStream(file);
                    Bitmap obtainedsign = gesture.toBitmap(360, 640, 10, Color.GREEN);
                    obtainedsign.compress(Bitmap.CompressFormat.PNG, 100, fout);
                    fout.flush();
                    fout.close();
                    MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                    Intent intent = new Intent(SignatureCapture.this, ObtainedSign.class);
                    intent.putExtra("pic_path", file.getAbsolutePath());
                    startActivity(intent);
                }catch (Exception e)
                {
                    Toast.makeText(SignatureCapture.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
