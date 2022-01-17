package com.leafoct.myrubbishclassify.mainFragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.leafoct.myrubbishclassify.R;
import com.leafoct.myrubbishclassify.instruction_fragment.InstructionAdapter;


public class Instruction extends Fragment {


    public Instruction() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_instruction, container, false);
        TabLayout tab=v.findViewById(R.id.tabs);
        InstructionAdapter ia=new InstructionAdapter(getContext(),getFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ViewPager vp=v.findViewById(R.id.instruction_pager);
        vp.setAdapter(ia);
        tab.setupWithViewPager(vp);
        TextView title=v.findViewById(R.id.title_name);
        title.setText("分类指南");
        return v;
    }

}
