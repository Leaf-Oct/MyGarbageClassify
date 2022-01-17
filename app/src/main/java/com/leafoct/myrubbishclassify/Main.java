package com.leafoct.myrubbishclassify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final BottomNavigationView bn=findViewById(R.id.bottom_navigation);
        final NavigationAdapter na=new NavigationAdapter(getSupportFragmentManager());
        final ViewPager viewpager=(ViewPager)findViewById(R.id.view_pager);
        viewpager.setAdapter(na);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bn.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home_page:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.menu_instruction:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.menu_about:
                        viewpager.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}
