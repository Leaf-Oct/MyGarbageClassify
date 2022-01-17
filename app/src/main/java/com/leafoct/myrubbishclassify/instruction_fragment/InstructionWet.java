package com.leafoct.myrubbishclassify.instruction_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leafoct.myrubbishclassify.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionWet extends Fragment {


    public InstructionWet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction_wet, container, false);
    }

}
