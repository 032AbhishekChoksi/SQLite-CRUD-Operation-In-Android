package com.abhishekchoksi.utuapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishekchoksi.utuapplication.DBHelper;
import com.abhishekchoksi.utuapplication.R;

public class EditStudentActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextView textViewBackStudentList;
    EditText editTextStudentName,editTextStudentEmail;
    Button buttonEditStudent;
    String oldemail ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            oldemail = bundle.getString("emailID");
            //Toast.makeText(this, ""+oldemail, Toast.LENGTH_SHORT).show();

            editTextStudentName = findViewById(R.id.editTextStudentName2);
            editTextStudentEmail = findViewById(R.id.editTextStudentEmail2);
            buttonEditStudent = findViewById(R.id.buttonEditStudent);
            textViewBackStudentList = findViewById(R.id.textViewBackStudentList);

            dbHelper = new DBHelper(EditStudentActivity.this,"StudentDB.db",null,1);

            Cursor cursor = dbHelper.displayStudentsByEmailEdit(oldemail);
            while (cursor.moveToNext()){
                editTextStudentName.setText(cursor.getString(1));
                editTextStudentEmail.setText(cursor.getString(2));
            }

            buttonEditStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = editTextStudentName.getText().toString();
                    String newemail = editTextStudentEmail.getText().toString();
                    dbHelper.updateStudentByEmail(Name,newemail,oldemail);
                    Toast.makeText(EditStudentActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    redirectAdminPanel();
                }
            });

            textViewBackStudentList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectAdminPanel();
                }
            });
        }
        else {
            redirectAdminPanel();
        }
    }
    private void redirectAdminPanel(){
        startActivity(new Intent(EditStudentActivity.this,AdminPanelActivity.class));
        finish();
    }
}