package com.yashjain.qrcodescanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class scanner extends AppCompatActivity {
    CodeScanner codeScanner;
    CodeScannerView ScanView;
    TextView resultData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ScanView=findViewById(R.id.scannerView);
        codeScanner=new CodeScanner(this,ScanView);
        resultData=findViewById(R.id.qrText);
         codeScanner.setDecodeCallback(new DecodeCallback() {
             @Override
             public void onDecoded(@NonNull Result result) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                    resultData.setText(result.getText());
                     }
                 });
             }
         });

         ScanView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 codeScanner.startPreview();
                 resultData.setText(" ");

             }
         });

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(scanner.this,"Camera Permission is Required",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
}