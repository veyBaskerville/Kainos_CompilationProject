package com.example.elective1compilationproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;

public class _14thGuidedExercise extends AppCompatActivity {

    Button sendSMS, sendBSMS, call;
    EditText phoneNo, message;
    ProgressDialog progressDialog;
    Intent smsIntent, callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._14th_guided_exercise);
        init();
        sendMessage();
        sendMessageBuiltIn();
        phoneCall();
    }

    public void init(){
        progressDialog = new ProgressDialog(this);
        sendSMS = findViewById(R.id.btnSMS);
        sendBSMS = findViewById(R.id.btnBSMS);
        call = findViewById(R.id.btnPhoneCall);
        phoneNo = findViewById(R.id.etPhoneNo);
        message = findViewById(R.id.etSMS);
    }

    public void sendMessage(){
        sendSMS.setOnClickListener(view -> {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo.getText().toString(), null, message.getText().toString(), null, null);

                progressDialog.setTitle("Sending...");
                progressDialog.setMessage("Message Sent!");
                progressDialog.show();

                new Handler().postDelayed(() -> progressDialog.cancel(), 3000);

            } catch (Exception e) {
                progressDialog.setTitle("Sending...");
                progressDialog.setMessage("Message was not delivered!");
                progressDialog.show();

                new Handler().postDelayed(() -> progressDialog.cancel(), 3000);
            }
        });
    }

    public void sendMessageBuiltIn(){
        sendBSMS.setOnClickListener(view -> {
            smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo.getText().toString()));
            smsIntent.putExtra("sms_body", message.getText().toString());
            startActivity(smsIntent);
        });
    }

    public void phoneCall(){
        call.setOnClickListener(view -> {
            callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNo.getText().toString()));
            if (ContextCompat.checkSelfPermission(_14thGuidedExercise.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(_14thGuidedExercise.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                try {
                    startActivity(callIntent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                    Toast.makeText(_14thGuidedExercise.this, "Permission not granted to make calls", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}