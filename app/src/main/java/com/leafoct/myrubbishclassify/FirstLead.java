package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.leafoct.myrubbishclassify.lead_fragment.LeadAdapter;

public class FirstLead extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_first_lead);
        ViewPager vp=findViewById(R.id.lead);
        LeadAdapter la=new LeadAdapter(getSupportFragmentManager());
        vp.setAdapter(la);

    }
}
