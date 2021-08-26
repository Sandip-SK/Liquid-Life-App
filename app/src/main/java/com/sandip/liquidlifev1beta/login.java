package com.sandip.liquidlifev1beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

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


    EditText etloginEmail;
    EditText etloginPassword;
    Button login;

    String loginEmail;
    String loginPassword;

    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        login = (Button) findViewById(R.id.loginButton);

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Check from database
//                // if true go to  Recent Requests intent

//                // else show error and redirect to Login
//            }
//        });

        try {
            String tablecreate="CREATE TABLE IF NOT EXISTS donordetails (donorname VARCHAR(30), donoremail VARCHAR(50), donorcity VARCHAR(25), donorcontact VARCHAR(15), donorbloodgroup VARCHAR(5), donorpassword VARCHAR(25))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(tablecreate);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onClickLogin(View view){
        etloginEmail = (EditText) findViewById(R.id.email2);
        etloginPassword = (EditText) findViewById(R.id.password2);

        loginEmail = etloginEmail.getText().toString();
        loginPassword = etloginPassword.getText().toString();

        try {
            Cursor c = myDatabase.rawQuery("SELECT * FROM donordetails WHERE donoremail='"+loginEmail+"' and donorpassword='"+loginPassword+"'",null);
            int rowsnum=c.getCount();
            int nameIndex=c.getColumnIndex("donorname");
            c.moveToFirst();
            String name = c.getString(nameIndex);
            if (rowsnum==1){
                Toast.makeText(this, "Welcome "+name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),recent.class);
                startActivity(intent);
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                builder.setMessage("Your Login Credentials does not exist.");
                builder.setTitle("Login Failed!");
                builder.setCancelable(false);
                builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),register.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Login Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}