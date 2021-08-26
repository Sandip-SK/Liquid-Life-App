package com.sandip.liquidlifev1beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.widget.TextView;

public class faq2 extends AppCompatActivity {
    String htmlText = "<ul>\n" +
            "                    <li>Donating blood is a safe process. A sterile needle is used only once for each donor and then discarded.</li>\n" +
            "                    <li>Blood donation is a simple four-step process: registration, medical history&nbsp;and&nbsp;mini-physical, donation and refreshments.</li>\n" +
            "                    <li>Every blood donor is given a mini-physical, checking the donor's temperature, blood pressure, pulse and&nbsp;hemoglobin to ensure it is safe for the donor to give blood.</li>\n" +
            "                    <li>The actual blood donation typically takes less than 10-12 minutes. The entire process, from the time you arrive to the time you leave, takes about an hour and 15 min.</li>\n" +
            "                    <li>The average adult has about 10 units of blood in his body. Roughly 1 unit is given during a donation.</li>\n" +
            "                    <li>A healthy donor may donate red blood cells every 56 days, or double red cells every 112 days.</li>\n" +
            "                    <li>A healthy donor may donate platelets as few as&nbsp;7 days apart, but a maximum of 24 times a year.</li>\n" +
            "                    <li>All donated blood is tested for HIV, hepatitis B and C, syphilis and other infectious diseases before it can be transfused to patients.</li>\n" +
            "                  </ul>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq2);

        TextView tvFaq2 = (TextView) findViewById(R.id.tvFaq2);
        tvFaq2.setText(HtmlCompat.fromHtml(htmlText,0));
    }
}