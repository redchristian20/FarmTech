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

public class IndexActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button,buttonIdentifyCrop,buttonCropInformation;
    TextView textView;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        button = findViewById(R.id.btn_logout);
        buttonIdentifyCrop = findViewById(R.id.btn_identify_crop);
        buttonCropInformation = findViewById(R.id.btn_crop_information);
        textView = findViewById(R.id.user_details);

        if(user == null)
        {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else
        {
            textView.setText(user.getEmail());
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonIdentifyCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageRecognitionActivity.class);
                startActivity(intent);
            }
        });
        buttonCropInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CropInformationActivity.class);
                startActivity(intent);
            }
        });

    }
}