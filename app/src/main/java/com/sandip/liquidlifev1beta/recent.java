package com.sandip.liquidlifev1beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class recent extends AppCompatActivity {

    SQLiteDatabase myDatabase;
    String namesD[];
    String contactD[];
    String bloodGroupD[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        RecyclerView programmingList = (RecyclerView) findViewById(R.id.programmingList);
        programmingList.setLayoutManager(new LinearLayoutManager(this));

        try {
            String createTable = "CREATE TABLE IF NOT EXISTS patientdetails (pname VARCHAR(30), pcontact VARCHAR(15), pcity VARCHAR(25),  pbloodgroup VARCHAR(5))";
            myDatabase = this.openOrCreateDatabase("liquidLife",MODE_PRIVATE,null);
            myDatabase.execSQL(createTable);

            Cursor c = myDatabase.rawQuery("SELECT * FROM patientdetails", null);
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

        }
    }
}