package com.leafoct.myrubbishclassify.instruction_fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class InstructionAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private static final String[] TAB_TITLES = new String[]{"可回收物","有害垃圾","湿垃圾","干垃圾"};
    private final Context mContext;
    public InstructionAdapter(Context context,@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mContext=context;
        fragments=new ArrayList<>();
        fragments.add(new InstructionRecycle());
        fragments.add(new InstructionHarmful());
        fragments.add(new InstructionWet());
        fragments.add(new InstructionDry());
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}
