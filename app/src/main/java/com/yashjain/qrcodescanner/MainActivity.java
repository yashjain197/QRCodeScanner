package com.yashjain.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import java.util.Scanner;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    EditText qrValue;
    Button genBtn,scnBtn;
    ImageView qrImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrValue=findViewById(R.id.textGenerate);
        genBtn=findViewById(R.id.GenerateQr);
        scnBtn=findViewById(R.id.scanBtn);
        qrImage=findViewById(R.id.qrImage);
        genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data=qrValue.getText().toString();
                if(data.isEmpty())
                {
                    qrValue.setError("Value Required.");
                }else{
                QRGEncoder qrgEncoder=new QRGEncoder(data, QRGContents.Type.TEXT,500);
                Bitmap qrBits= qrgEncoder.getBitmap();
                qrImage.setImageBitmap(qrBits);
                }

            }
        });

        scnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, scanner.class);
                startActivity(intent);
            }
        });

    }
}