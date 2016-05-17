package com.example.sandeep.signaturecapture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class ObtainedSign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obtained_sign);
        TextView path=(TextView)findViewById(R.id.textView3);
        ImageView obsign=(ImageView)findViewById(R.id.imageView);

        Intent intent=getIntent();

        Bitmap pic=BitmapFactory.decodeFile(intent.getStringExtra("pic_path"));
        path.setText("Image Location: "+intent.getStringExtra("pic_path"));

        obsign.setImageBitmap(pic);


    }
}
