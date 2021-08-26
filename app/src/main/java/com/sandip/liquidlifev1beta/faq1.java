package com.sandip.liquidlifev1beta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.Bundle;
import android.widget.TextView;

public class faq1 extends AppCompatActivity {
    String htmlText = "<ul>\n" +
            "                    <li>Every year our nation requires about <strong>5 Crore\n" +
            "                        units</strong> of blood, out of which only a meager <strong>2.5 Crore units</strong> of blood are available.</li>\n" +
            "                    <li>The <strong>gift of blood</strong> is the <strong>gift\n" +
            "                        of life</strong>. There is no substitute for human\n" +
            "                      blood.</li>\n" +
            "                    <li>Every two seconds someone needs blood.</li>\n" +
            "                    <li>More than 38,000 blood donations are needed every day.</li>" +
            "<li>A total&nbsp;of 30 million blood components are transfused each year.</li>\n" +
            "                    <li>The average red blood cell transfusion is approximately 3 pints.</li>\n" +
            "                    <li>The blood type most often requested by hospitals is Type O.</li>\n" +
            "                    <li>Sickle cell patients can require frequent blood transfusions throughout their lives.</li>\n" +
            "                    <li>More than 1 million new people are diagnosed with cancer each year. Many of them will need blood, sometimes daily, during their chemotherapy treatment.</li>\n" +
            "                    <li>A single car accident victim can require as many as 100 units of blood</li></ul>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq1);

        TextView tvFaq1 = (TextView) findViewById(R.id.tvFaq1);
        tvFaq1.setText(HtmlCompat.fromHtml(htmlText,0));
    }
}