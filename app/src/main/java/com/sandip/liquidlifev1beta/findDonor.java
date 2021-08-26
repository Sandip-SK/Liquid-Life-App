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

public class findDonor extends AppCompatActivity {

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
    //MENU END


    //Spinner spinnerPatient;
    EditText etPatientName;
    EditText etPatientContact;
    EditText etPatientCity;
    Spinner spinnerPatient;
    Button findDonorButton;

    String patientName;
    String patientContact;
    String patientLocation;
    String patientBloodGroup;

    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_donor);

        String pBloodGrp[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

        spinnerPatient = (Spinner) findViewById(R.id.spPatientBloodGroup);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new  ArrayAdapter(this,
                android.R.layout.simple_spinner_item,pBloodGrp);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerPatient.setAdapter(adapter);

        try {
            String createTable = "CREATE TABLE IF NOT EXISTS patientdetails (pname VARCHAR(30), pcontact VARCHAR(15), pcity VARCHAR(25),  pbloodgroup VARCHAR(5))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(createTable);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void findDonor(View view){
        //Getting all the Elements
        etPatientName = (EditText) findViewById(R.id.etPatientName);
        etPatientContact = (EditText) findViewById(R.id.etPatientContact);
        etPatientCity = (EditText) findViewById(R.id.etPatientLocation);
        findDonorButton = (Button) findViewById(R.id.findDonorButton);

        // Storing as String
        patientName = etPatientName.getText().toString();
        patientContact = etPatientContact.getText().toString();
        patientLocation = etPatientCity.getText().toString();
        patientBloodGroup = spinnerPatient.getSelectedItem().toString();
        Log.i("pname",patientName);
        Toast.makeText(findDonor.this, " "+patientBloodGroup, Toast.LENGTH_SHORT).show();
        try {
            String insertQuery="INSERT INTO patientdetails(pname,pcontact,pcity,pbloodgroup) VALUES ('"+patientName+"','"+patientContact+"','"+patientLocation+"','"+patientBloodGroup+"');";
            myDatabase.execSQL(insertQuery);
            Toast.makeText(findDonor.this, patientName+" Request is Sent", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),availableDonors.class);
            intent.putExtra("pePatientBloodGroup",patientBloodGroup);
            intent.putExtra("pePatientLocation",patientLocation);
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}