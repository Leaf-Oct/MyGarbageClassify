package com.leafoct.myrubbishclassify.mainFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.leafoct.myrubbishclassify.Exam;
import com.leafoct.myrubbishclassify.R;
import com.leafoct.myrubbishclassify.Search;

public class homepage extends Fragment {


    public homepage() {
    }

    private Button exam;
    private ImageButton search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_homepage, container, false);
        search=v.findViewById(R.id.to_search);
        exam=v.findViewById(R.id.come_to_exam);
        search=v.findViewById(R.id.to_search);
        initial_exam();
        initial_search();
        return v;
    }
    private void initial_exam(){
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Exam.class));
            }
        });
    }
    private void initial_search(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Search.class));
            }
        });
    }
}
