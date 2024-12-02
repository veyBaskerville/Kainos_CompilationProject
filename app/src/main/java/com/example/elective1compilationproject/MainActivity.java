package com.example.elective1compilationproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button, button2, button3, button4, button5, btn1stguided, btn2ndguided, btn3rdguided, btn4thguided, btn5thguided, btn6thguided, btn7thguided, btn8thguided, btn9thguided;
    Button btn10thguided, btn11thguided, btn11thguided3, btn12thguided, btn13thguided, btn14thguided, btn15thguided, btnSplashScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.btnCCJitters);
        button.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, MP_CCJitters.class);
            startActivity(i);

        });
        button2 = findViewById(R.id.btnPayrollSQL);
        button2.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, MP_PayrollSQL.class);
            startActivity(i);

        });
        button3 = findViewById(R.id.btnBookLibrary);
        button3.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, BookLibraryApp.class);
            startActivity(i);

        });
        button4 = findViewById(R.id.btnPayroll);
        button4.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this, MP_Payroll.class);
            startActivity(i);

        });

        button5 = findViewById(R.id.btnCalculator);
        button5.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, MP_Calculator.class);
            startActivity(i);
        });

        btnSplashScreen = findViewById(R.id.btnSplashScreen);
        btnSplashScreen.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, Splash.class);
            startActivity(i);
        });

        btn1stguided = findViewById(R.id.btn1stguided);
        btn1stguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _1stGuidedExercise.class);
            startActivity(i);
        });
        btn2ndguided = findViewById(R.id.btn2ndguided);
        btn2ndguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _2ndGuidedExercise.class);
            startActivity(i);
        });
        btn3rdguided= findViewById(R.id.btn3rdguided);
        btn3rdguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _3rdGuidedExercise.class);
            startActivity(i);
        });

        btn4thguided= findViewById(R.id.btn4thguided);
        btn4thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _4thGuidedExercise.class);
            startActivity(i);
        });

        btn5thguided= findViewById(R.id.btn5thguided);
        btn5thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _5thGuidedExercise.class);
            startActivity(i);
        });

        btn6thguided= findViewById(R.id.btn6thguided);
        btn6thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _6thGuidedExercise.class);
            startActivity(i);
        });
        btn7thguided = findViewById(R.id.btn7thguided);
        btn7thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _7thGuidedExercise.class);
            startActivity(i);
        });

        btn8thguided= findViewById(R.id.btn8thguided);
        btn8thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _8thGuidedExercise.class);
            startActivity(i);
        });

        btn9thguided = findViewById(R.id.btn9thguided);
        btn9thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _9thGuidedExercise.class);
            startActivity(i);
        });

        btn10thguided= findViewById(R.id.btn10thguided);
        btn10thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _10thGuidedExercise.class);
            startActivity(i);
        });

        btn11thguided = findViewById(R.id.btn11thguided);
        btn11thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _11thGuidedExercise.class);
            startActivity(i);
        });

        btn11thguided3 = findViewById(R.id.btn11thguided3);
        btn11thguided3.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _11secondGuidedExercise.class);
            startActivity(i);
        });

        btn12thguided = findViewById(R.id.btn12thguided);
        btn12thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _12thGuidedExercise.class);
            startActivity(i);
        });

        btn13thguided = findViewById(R.id.btn13thguided);
        btn13thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _13thGuidedExercise.class);
            startActivity(i);
        });

        btn14thguided = findViewById(R.id.btn14thguided);
        btn14thguided.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this, _14thGuidedExercise.class);
            startActivity(i);
        });



    }
}