package com.example.elective1compilationproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class _13thGuidedExercise extends AppCompatActivity {

    Button send, capturePic;
    EditText receiver, subject, message;
    ImageView pic;
    Intent intent, chooser;
    public static final int RequestPermissionCode = 1;
    public static final int CAMERA_REQUEST_CODE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._13th_guided_exercise);
        init();
        enableRuntimePermission();
        setupSendEmail();
        setupCapturePic();
    }

    // Initialize UI components
    public void init() {
        receiver = findViewById(R.id.etReceiver);
        subject = findViewById(R.id.etSubject);
        message = findViewById(R.id.etMessage);
        pic = findViewById(R.id.ivPic);
        send = findViewById(R.id.btnSend);
        capturePic = findViewById(R.id.btnCapturePic);
    }

    // Enable runtime permissions for camera
    public void enableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Toast.makeText(
                    getApplicationContext(),
                    "CAMERA permission allows us to access the camera app.",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    RequestPermissionCode
            );
        }
    }

    // Set up email sending functionality
    public void setupSendEmail() {
        send.setOnClickListener(view -> {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:")); // Email protocol
            String[] to = {receiver.getText().toString()};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
            intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
            intent.setType("message/rfc822");

            chooser = Intent.createChooser(intent, "Send Email");

            if (receiver.getText().toString().isEmpty()) {
                receiver.setError("Email required!");
            } else {
                startActivity(chooser);
            }
        });
    }

    // Set up the capture picture functionality
    public void setupCapturePic() {
        capturePic.setOnClickListener(view -> {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        });
    }

    // Handle camera results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                pic.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}