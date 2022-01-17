package com.leafoct.myrubbishclassify.lead_fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class LeadAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public LeadAdapter(FragmentManager fm) {
        super(fm);
        fragments=new ArrayList<>();
        for(int i=0;i<5;i++){
            fragments.add(fragment_set.newInstance(i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
