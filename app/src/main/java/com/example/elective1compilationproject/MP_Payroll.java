package com.example.elective1compilationproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MP_Payroll extends AppCompatActivity {
    private Spinner employeeID, positionCode, daysWorked;
    private TextView employeeName;
    private double positionValue, daysWorkedValue;
    private RadioGroup civilStatus;
    private Button compute, clear;

    private double BasicPay, taxRate, SSSRate, SSSContribution, WithholdingTax, NetPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_problem4);

        // Initializing views
        employeeID = findViewById(R.id.spinnerEmployeeID);
        employeeName = findViewById(R.id.tvName);
        positionCode = findViewById(R.id.spinnerPosition);
        civilStatus = findViewById(R.id.radioGroupTax);
        daysWorked = findViewById(R.id.spinnerDaysWorked);
        compute = findViewById(R.id.btnCompute);
        clear = findViewById(R.id.btnClear);

        // Setup Employee Spinner
        HashMap<String, String> employeeData = new HashMap<>();
        employeeData.put("EMP01", "Asilito Caasi");
        employeeData.put("EMP02", "Gwyneth Landero");
        employeeData.put("EMP03", "April Faustino");
        employeeData.put("EMP04", "John Harvey Hingco");
        employeeData.put("EMP05", "Michael Bargabino");

        List<String> employeeIds = new ArrayList<>(employeeData.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeID.setAdapter(adapter);

        employeeID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = parent.getItemAtPosition(position).toString();
                employeeName.setText(employeeData.get(selectedId));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeeName.setText("");
            }
        });

        // Setup Position Spinner
        HashMap<String, Double> positionData = new HashMap<>();
        positionData.put("A", 500.0);
        positionData.put("B", 400.0);
        positionData.put("C", 300.0);

        ArrayAdapter<String> positionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(positionData.keySet()));
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionCode.setAdapter(positionAdapter);

        positionCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionValue = positionData.get(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                positionValue = 0.0;
            }
        });

        // Setup Days Worked Spinner
        String[] daysArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30"};
        ArrayAdapter<String> daysWorkedAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, daysArray);
        daysWorkedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysWorked.setAdapter(daysWorkedAdapter);

        daysWorked.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daysWorkedValue = Double.parseDouble(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Clear button functionality
        clear.setOnClickListener(v -> {
            employeeID.setSelection(0);
            employeeName.setText("");
            positionCode.setSelection(0);
            daysWorked.setSelection(0);
            civilStatus.clearCheck();
            Toast.makeText(this, "Fields cleared", Toast.LENGTH_SHORT).show();
        });

        // Compute button functionality
        compute.setOnClickListener(v -> {
            if (validateFields()) {
                computePayroll();
            }
        });
    }

    private boolean validateFields() {
        if (civilStatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select a civil status.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void computePayroll() {
        // Compute Basic Pay
        BasicPay = daysWorkedValue * positionValue;

        // SSS Contribution calculation
        SSSRate = BasicPay >= 10000 ? 0.07 : BasicPay >= 5000 ? 0.05 : BasicPay >= 1000 ? 0.03 : 0.01;
        SSSContribution = BasicPay * SSSRate;

        // Set tax rate based on civil status
        RadioButton selectedRadioButton = findViewById(civilStatus.getCheckedRadioButtonId());
        if (selectedRadioButton != null) {
            String civilStatusText = selectedRadioButton.getText().toString();
            if (civilStatusText.equals("Single")) {
                taxRate = 0.15; // Single has a 15% tax rate
            } else if (civilStatusText.equals("Married")) {
                taxRate = 0.10; // Married has a 10% tax rate
            } else {
                taxRate = 0.05; // Default tax rate
            }
        }

        // Withholding tax calculation
        WithholdingTax = BasicPay * taxRate;

        // Net Pay calculation
        NetPay = BasicPay - (SSSContribution + WithholdingTax);

        // Show computed values in the Result Activity
        MP_PayrollSQLDatabaseHelper dbHelper = new MP_PayrollSQLDatabaseHelper(this);
        dbHelper.insertPayrollData(
                employeeID.getSelectedItem().toString(),
                employeeName.getText().toString(),
                positionCode.getSelectedItem().toString(),
                ((RadioButton) findViewById(civilStatus.getCheckedRadioButtonId())).getText().toString(),
                daysWorked.getSelectedItem().toString(),
                BasicPay,
                SSSContribution,
                WithholdingTax,
                NetPay
        );

        // Start result activity
        Intent intent = new Intent(this, MP_PayrollResult.class);
        intent.putExtra("EmployeeID", employeeID.getSelectedItem().toString());
        intent.putExtra("EmployeeName", employeeName.getText().toString());
        intent.putExtra("PositionCode", positionCode.getSelectedItem().toString());

// Get the selected RadioButton from the RadioGroup for civil status
        int selectedId = civilStatus.getCheckedRadioButtonId();
        selectedRadioButton = findViewById(selectedId);  // Declare and initialize the RadioButton here
        String civilStatusValue = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Not specified";

// Add the civil status value to the intent
        intent.putExtra("CivilStatus", civilStatusValue);

// Add the rest of the payroll details to the intent
        intent.putExtra("DaysWorked", daysWorked.getSelectedItem().toString());
        intent.putExtra("BasicPay", BasicPay);
        intent.putExtra("SSSContribution", SSSContribution);
        intent.putExtra("WithholdingTax", WithholdingTax);
        intent.putExtra("NetPay", NetPay);

// Start the result activity
        startActivity(intent);
    }
}