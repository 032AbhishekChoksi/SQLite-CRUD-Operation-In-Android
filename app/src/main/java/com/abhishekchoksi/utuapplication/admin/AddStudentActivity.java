package com.abhishekchoksi.utuapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishekchoksi.utuapplication.DBHelper;
import com.abhishekchoksi.utuapplication.R;

public class AddStudentActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView textViewBack;
    EditText editTextStudentName,editTextStudentEmail;
    Button buttonAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentEmail = findViewById(R.id.editTextStudentEmail);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        textViewBack = findViewById(R.id.textViewBack);

        dbHelper = new DBHelper(AddStudentActivity.this,"StudentDB.db",null,1);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextStudentName.getText().toString();
                String email = editTextStudentEmail.getText().toString();

                try
                {
                    dbHelper.addStudent(name,email);
                    Toast.makeText(getApplicationContext(),"Student Added Successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStudentActivity.this,AdminPanelActivity.class));
                finish();
            }
        });
    }
}