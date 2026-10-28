
package com.example.agripulse;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    TextView t1;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        t1 = findViewById(R.id.t1);

        dbHelper = new DatabaseHelper(this);
    }

    public void insert(View view) {

        String crop = e1.getText().toString().trim();
        String planting = e2.getText().toString().trim();
        String irrigation = e3.getText().toString().trim();
        String harvest = e4.getText().toString().trim();

        if (crop.isEmpty() || planting.isEmpty()
                || irrigation.isEmpty() || harvest.isEmpty()) {

            Toast.makeText(this, "Enter all details",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        boolean result = dbHelper.addRecord(
                crop, planting, irrigation, harvest);

        if (result) {
            Toast.makeText(this, "Record added successfully",
                    Toast.LENGTH_SHORT).show();

            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");

        } else {
            Toast.makeText(this, "Failed to add record",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View view) {
        String records = dbHelper.getAllRecords();
        t1.setText(records);
    }
}
