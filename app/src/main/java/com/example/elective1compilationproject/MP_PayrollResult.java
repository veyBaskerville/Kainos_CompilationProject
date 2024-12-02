package com.example.elective1compilationproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MP_PayrollResult extends AppCompatActivity {
    private TextView tvID, tvName, tvPosition, tvCivil, tvDays, tvBasicPay, tvSSS, tvTax, tvNetPay;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_machine_problem4_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize TextViews and Button
        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvPosition = findViewById(R.id.tvPosition);
        tvCivil = findViewById(R.id.tvCivil);
        tvDays = findViewById(R.id.tvDays);
        tvBasicPay = findViewById(R.id.tvBasicPay);
        tvSSS = findViewById(R.id.tvSSS);
        tvTax = findViewById(R.id.tvTax);
        tvNetPay = findViewById(R.id.tvNetPay);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Get the intent that started this activity
        Intent intent = getIntent();

        // Retrieve the data passed from the previous activity
        String employeeID = intent.getStringExtra("EmployeeID");
        String employeeName = intent.getStringExtra("EmployeeName");
        String positionCode = intent.getStringExtra("PositionCode");
        String civilStatus = intent.getStringExtra("CivilStatus");
        String daysWorked = intent.getStringExtra("DaysWorked");
        double basicPay = intent.getDoubleExtra("BasicPay", 0);
        double sssContribution = intent.getDoubleExtra("SSSContribution", 0);
        double withholdingTax = intent.getDoubleExtra("WithholdingTax", 0);
        double netPay = intent.getDoubleExtra("NetPay", 0);

        // Set the values to the TextViews
        tvID.setText(employeeID);
        tvName.setText(employeeName);
        tvPosition.setText(positionCode);
        tvCivil.setText(civilStatus);
        tvDays.setText(daysWorked);

        // Format and display currency values
        tvBasicPay.setText(formatCurrency(basicPay));
        tvSSS.setText(formatCurrency(sssContribution));
        tvTax.setText(formatCurrency(withholdingTax));
        tvNetPay.setText(formatCurrency(netPay));
    }

    // Method to format the currency in Philippine Peso
    private String formatCurrency(double amount) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
        return currencyFormat.format(amount);
    }
}