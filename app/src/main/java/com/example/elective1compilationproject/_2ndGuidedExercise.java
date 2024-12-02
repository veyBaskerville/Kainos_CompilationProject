package com.example.elective1compilationproject;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class _2ndGuidedExercise extends AppCompatActivity {
    EditText name;
    Button click;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout._2nd_guided_exercise);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = findViewById(R.id.etFullName);
        click = findViewById(R.id.btnClick);

        showResult();
    }

    public void showResult() {
        click.setOnClickListener(view -> {
            String userName = name.getText().toString();
            if (!userName.isEmpty()) {
                toast = Toast.makeText(getApplicationContext(),
                        "Hello " + userName + "! \nWelcome to Android Development!", Toast.LENGTH_SHORT);
                // Set layout of toast
                toast.setGravity(Gravity.CENTER, 0, 0);
                // Display the toast
                toast.show();
            } else {
                Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
