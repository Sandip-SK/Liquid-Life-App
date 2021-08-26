package com.sandip.liquidlifev1beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class register extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.menuHome:
                Log.i("Item Selected","menuHome");
                Intent intentHome = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intentHome);
                //Toast.makeText(this, "Settings Tapped", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuRegister:
                Log.i("Item Selected","menuRegister");
                Intent intentRegister = new Intent(getApplicationContext(),register.class);
                startActivity(intentRegister);
                return true;
            case R.id.menuLogin:
                Log.i("Item Selected","menuLogin");
                Intent intentLogin = new Intent(getApplicationContext(),login.class);
                startActivity(intentLogin);
                return true;
            case R.id.menuSearch:
                Log.i("Item Selected","menuSearch");
                Intent intentSearch = new Intent(getApplicationContext(),search.class);
                startActivity(intentSearch);
                return true;
            case R.id.menuAbout:
                Log.i("Item Selected","menuAbout");
                Intent intentAbout = new Intent(getApplicationContext(),about.class);
                startActivity(intentAbout);
                return true;
            default:
                return false;
        }
    }

    // MENU END


    EditText nameT ;
    EditText emailT ;
    EditText locationT ;
    EditText contactT ;
    EditText passwordT;
    Spinner spinner;

    Button register ;
    String name ;
    String email ;
    String location ;
    String contact ;
    String password;
    String bloodGroup;

    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String bloodGrp[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

        spinner = (Spinner) findViewById(R.id.bloodGroup);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new  ArrayAdapter(this,
                 android.R.layout.simple_spinner_item,bloodGrp);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        try {
            String tablecreate="CREATE TABLE IF NOT EXISTS donordetails (donorname VARCHAR(30), donoremail VARCHAR(50), donorcity VARCHAR(25), donorcontact VARCHAR(15), donorbloodgroup VARCHAR(5), donorpassword VARCHAR(25))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(tablecreate);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void onClickRegister(View view){
        // Getting All Data
        nameT = (EditText) findViewById(R.id.etName);
        emailT = (EditText) findViewById(R.id.etEmail);
        locationT = (EditText) findViewById(R.id.etLocation);
        contactT = (EditText) findViewById(R.id.etContact);
        passwordT = (EditText) findViewById(R.id.etPassword);
        register = (Button) findViewById(R.id.registerButton);

        // Variable declaration
        name = nameT.getText().toString();
        email = emailT.getText().toString();
        location = locationT.getText().toString();
        contact = contactT.getText().toString();
        password = passwordT.getText().toString();
        bloodGroup = spinner.getSelectedItem().toString();
        try {
            String insertQuery = "INSERT INTO donordetails(donorname,donoremail,donorcity,donorcontact,donorbloodgroup,donorpassword) VALUES ('"+name+"','"+email+"','"+location+"','"+contact+"','"+bloodGroup+"','"+password+"');";
            myDatabase.execSQL(insertQuery);
            Toast.makeText(register.this, ""+name+" Your Details are Saved", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}