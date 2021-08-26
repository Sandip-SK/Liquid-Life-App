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

public class searchresults extends AppCompatActivity {

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
        setContentView(R.layout.activity_searchresults);

        Intent intent = getIntent();
        pePatientBloodGroup = intent.getStringExtra("searchBloodGroup");
        pePatientLocation = intent.getStringExtra("searchCity");

        RecyclerView programmingList = (RecyclerView) findViewById(R.id.programmingList);
        programmingList.setLayoutManager(new LinearLayoutManager(this));

        try {
            String createTable = "CREATE TABLE IF NOT EXISTS patientdetails (pname VARCHAR(30), pcontact VARCHAR(15), pcity VARCHAR(25),  pbloodgroup VARCHAR(5))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(createTable);

            Cursor c = myDatabase.rawQuery("SELECT * FROM patientdetails WHERE pcity='"+pePatientLocation+"' and pbloodgroup='"+pePatientBloodGroup+"'", null);
            int nameIndex = c.getColumnIndex("pname");
            int contactIndex = c.getColumnIndex("pcontact");
            int bloodGroupIndex = c.getColumnIndex("pbloodgroup");
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