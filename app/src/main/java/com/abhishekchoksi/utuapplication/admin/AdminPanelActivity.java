package com.abhishekchoksi.utuapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abhishekchoksi.utuapplication.DBHelper;
import com.abhishekchoksi.utuapplication.MainActivity;
import com.abhishekchoksi.utuapplication.R;

import java.util.ArrayList;

public class AdminPanelActivity extends AppCompatActivity {

    DBHelper dbHelper;
//    TextView textViewResult;
    Spinner spinner;
    ListView listViewStudent;
    ArrayList listForView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        //textViewResult = findViewById(R.id.textViewResult);
        spinner = findViewById(R.id.spinner);

        dbHelper = new DBHelper(AdminPanelActivity.this,"StudentDB.db",null,1);

        //dbHelper.displayAllStudents(textViewResult);

        //Display Data In Spinner
        ArrayList list = dbHelper.displayStudentInSpinner();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        spinner.setVisibility(View.INVISIBLE);
        spinner.setAdapter(adapter);

        // Display Data In List View
        listForView = dbHelper.displayAllStudent();
        listViewStudent = findViewById(R.id.listViewStudent);
        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listForView);
        listViewStudent.setAdapter(adp);

        //Context Menu
        registerForContextMenu(listViewStudent);

        //Spinner Selected Value Display In List View
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String email = list.get(position).toString();
////                Toast.makeText(AdminPanelActivity.this, ""+email, Toast.LENGTH_SHORT).show();
//                listForView = dbHelper.displayStudentsByEmail(email);
//                ArrayAdapter adapter = new ArrayAdapter(AdminPanelActivity.this,android.R.layout.simple_list_item_1,listForView);
//                listViewStudent.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    // Context Menu Methods
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.action_admin_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            case R.id.action_admin_edit:
                String str = listForView.get(i.position).toString();
                String[] strArr = str.split(",");
                String email = strArr[2].toString();
                Intent intent = new Intent(AdminPanelActivity.this,EditStudentActivity.class);
                intent.putExtra("emailID",email);
                startActivity(intent);

                //Toast.makeText(this, ""+ strArr[2] , Toast.LENGTH_SHORT).show();
                return true;
        }
        return (super.onContextItemSelected(item));
    }

    //Options Menu Methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.AddStudentMenu:
                startActivity(new Intent(AdminPanelActivity.this,AddStudentActivity.class));
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }


}