package com.capstone.farmtech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.capstone.farmtech.authentication.Login;
import com.capstone.farmtech.crop_information.CropInformationActivity;
import com.capstone.farmtech.image_recognition.ImageRecognitionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Going to home page
        Intent i = new Intent(MainActivity.this,IndexActivity.class);
        startActivity(i);

    }
}