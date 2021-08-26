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
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button donorLogin = (Button) findViewById(R.id.donorLogin);
        Button register = (Button) findViewById(R.id.register);
        Button findDonor = (Button) findViewById(R.id.findDonor);
        Button faq = (Button) findViewById(R.id.faq);

        donorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to Donor Login Activity
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        // Register OnClick
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to Registration Page
                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivity(intent);
            }
        });

        // Find Donor Activity
        findDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to Find Donor Form
                Intent intent = new Intent(getApplicationContext(),findDonor.class);
                startActivity(intent);
            }
        });

        // FAQ Activity
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to FAQ Activity
                Intent intent = new Intent(getApplicationContext(),faq.class);
                startActivity(intent);
            }
        });
    }
}