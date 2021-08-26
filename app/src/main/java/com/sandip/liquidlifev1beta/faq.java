package com.sandip.liquidlifev1beta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class faq extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ListView lvFaq = (ListView) findViewById(R.id.lvFaq);
        String[] faqs = {"Blood donation needs","Facts about blood donation process","Blood supply facts","Facts about blood and its component","Facts about Donors"
                ,"Frequency of Blood Types","Examples of Blood Use"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,faqs);

        lvFaq.setAdapter(arrayAdapter);

        lvFaq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),faq1.class);
                        startActivity(intent);

                    case 1:
                        Intent intent2 = new Intent(getApplicationContext(),faq2.class);
                        startActivity(intent2);
                    default:
                        Toast.makeText(faq.this, "Click on FAQ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}