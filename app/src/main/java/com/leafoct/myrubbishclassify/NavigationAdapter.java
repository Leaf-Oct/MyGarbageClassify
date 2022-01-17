package com.leafoct.myrubbishclassify;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.leafoct.myrubbishclassify.mainFragment.Instruction;
import com.leafoct.myrubbishclassify.mainFragment.homepage;
import com.leafoct.myrubbishclassify.mainFragment.mine;

import java.util.ArrayList;
import java.util.List;

public class NavigationAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public NavigationAdapter(FragmentManager fm){
        super(fm);
        fragments=new ArrayList<>();
        fragments.add(new homepage());
        fragments.add(new Instruction());
        fragments.add(new mine());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
