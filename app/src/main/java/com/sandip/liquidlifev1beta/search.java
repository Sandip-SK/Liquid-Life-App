package com.sandip.liquidlifev1beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class search extends AppCompatActivity {

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


    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    String searchCity;
    String searchBloodGroup;
    String cities[] = {"Ranchi","Delhi","Kolkata","Raorkela","Kanyakumari","Kashmir","Dehradun","Ranthambore","Hyderabad","Vellore","Vizag"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String bloodGrp[] = {"A+","A-","B+","B-","O+","O-","AB+","AB-"};

        spinner = (Spinner) findViewById(R.id.searchBloodGroup);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new  ArrayAdapter(this,
                android.R.layout.simple_spinner_item,bloodGrp);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Adapter for Auto Complete
        ArrayAdapter<String> autoTextAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,cities);
        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.actvCity);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(autoTextAdapter);

    }

    public void onClickSearch(View view){
        searchCity=autoCompleteTextView.getText().toString();
        searchBloodGroup=spinner.getSelectedItem().toString();
        System.out.println(searchCity+"  "+searchBloodGroup);

        Intent intent = new Intent(getApplicationContext(),searchresults.class);
        intent.putExtra("searchCity",searchCity);
        intent.putExtra("searchBloodGroup",searchBloodGroup);
        startActivity(intent);
    }
}