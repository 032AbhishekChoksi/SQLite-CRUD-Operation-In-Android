package com.abhishekchoksi.utuapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.abhishekchoksi.utuapplication.R;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUserName,editTextUserPassword;
    Button buttonLogin;
    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserPassword = findViewById(R.id.editTextUserPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewMessage = findViewById(R.id.textViewMessage);

        SharedPreferences pref = getSharedPreferences("AdminData",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("username","Admin");
        editor.putString("password","Admin");

        editor.commit();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextUserName.getText().toString();
                String password = editTextUserPassword.getText().toString();

                //SharedPreference Data
                String sharedName = pref.getString("username","NULL");
                String sharedPassword = pref.getString("password","NULL");

                if(name.equals(sharedName))
                {
                    if(password.equals(sharedPassword))
                    {
                        startActivity(new Intent(LoginActivity.this, AdminPanelActivity.class));
                        finish();
                    }
                    else
                    {
                        textViewMessage.setText("Invalid User Password");
                    }
                }
                else
                {
                    textViewMessage.setText("Invalid User Name");
                }
            }
        });
    }
}