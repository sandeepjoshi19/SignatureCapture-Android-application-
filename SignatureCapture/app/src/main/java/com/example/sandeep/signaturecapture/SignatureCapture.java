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
        final GestureOverlayView sign = (GestureOverlayView) findViewById(R.id.gestureOverlayView);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sign.setDrawingCacheEnabled(true);
                    String path= Environment.getExternalStorageDirectory().toString();
                    File file=new File(path,"sign.png");
                    OutputStream fout=new FileOutputStream(file);
                    Bitmap obtainedsign = sign.getGesture().toBitmap(300,300,10,Color.BLUE);
                    obtainedsign.compress(Bitmap.CompressFormat.PNG, 100, fout);
                    fout.flush();
                    fout.close();
                    MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                    Intent intent = new Intent(SignatureCapture.this, ObtainedSign.class);
                    intent.putExtra("pic_path", file.getAbsolutePath());
                    startActivity(intent);
                    sign.clear(true);
                }catch (Exception e)
                {

                }
            }
        });



    }
}
