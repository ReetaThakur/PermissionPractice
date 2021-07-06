package com.example.permissionpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private static final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[]permission={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(MainActivity.this,permission,REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"Storage and location permission granted",Toast.LENGTH_LONG).show();
        }else if(grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_DENIED){
            Toast.makeText(MainActivity.this,"Storage is Granted but Location is denied",Toast.LENGTH_LONG).show();
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"Storage is denied but Location is granted",Toast.LENGTH_LONG).show();
        }else if(grantResults[0]==PackageManager.PERMISSION_DENIED&&grantResults[1]==PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0]) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[1])) {
                Toast.makeText(MainActivity.this, "Both Permission Denied", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this, "Both Permission Denied go to setting for granted", Toast.LENGTH_LONG).show();
            }
        }
    }
}