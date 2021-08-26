package com.sandip.liquidlifev1beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class availableDonors extends AppCompatActivity {

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


    SQLiteDatabase myDatabase;
    String namesD[];
    String contactD[];
    String bloodGroupD[];
    String pePatientBloodGroup;
    String pePatientLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_donors);

        Intent intent = getIntent();
        pePatientBloodGroup = intent.getStringExtra("pePatientBloodGroup");
        pePatientLocation = intent.getStringExtra("pePatientLocation");

        RecyclerView programmingList = (RecyclerView) findViewById(R.id.programmingList);
        programmingList.setLayoutManager(new LinearLayoutManager(this));
//        String []languages ={"Java","JavaScript","C#","php","C","C++","Python","Java","JavaScript","C#","php","C","C++","Python","Java","JavaScript","C#","php","C","C++","Python"};
//        String []price ={"2000","4000","5000","2000","1500","1800","3500","2000","4000","5000","2000","1500","1800","3500","2000","4000","5000","2000","1500","1800","3500"};
//        programmingList.setAdapter(new ProgrammingAdapter(languages, price));

        try {
            String tablecreate="CREATE TABLE IF NOT EXISTS donordetails (donorname VARCHAR(30), donoremail VARCHAR(50), donorcity VARCHAR(25), donorcontact VARCHAR(15), donorbloodgroup VARCHAR(5), donorpassword VARCHAR(25))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(tablecreate);
            Cursor c = myDatabase.rawQuery("SELECT * FROM donordetails WHERE donorcity='"+pePatientLocation+"' and donorbloodgroup='"+pePatientBloodGroup+"'", null);
            int nameIndex = c.getColumnIndex("donorname");
            int contactIndex = c.getColumnIndex("donorcontact");
            int bloodGroupIndex = c.getColumnIndex("donorbloodgroup");
            int rowsnum = c.getCount();
            namesD=new String[rowsnum];
            contactD=new String[rowsnum];
            bloodGroupD=new String[rowsnum];
            c.moveToFirst();

            //Filling the String arrays with database values
            for (int i=0;i<rowsnum;i++){
                namesD[i]=c.getString(nameIndex);
                contactD[i]=c.getString(contactIndex);
                bloodGroupD[i]=c.getString(bloodGroupIndex);
                System.out.println(namesD[i]);
                System.out.println(contactD[i]);
                System.out.println(bloodGroupD[i]);
                c.moveToNext();
            }
            programmingList.setAdapter(new ProgrammingAdapter(namesD, contactD, bloodGroupD));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}