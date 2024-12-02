package com.example.elective1compilationproject;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    Intent intent;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        title = findViewById(R.id.textView7);

        // Add fade-in animation
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(title, "alpha", 0f, 1f);
        fadeIn.setDuration(2000); // 2 seconds

        // Start the animation
        fadeIn.start();
        splashScreen();
    }

    public void splashScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(Splash.this, BookLibraryHome.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
