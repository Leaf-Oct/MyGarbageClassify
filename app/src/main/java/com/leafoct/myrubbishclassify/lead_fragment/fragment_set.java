package com.leafoct.myrubbishclassify.lead_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.leafoct.myrubbishclassify.Login;
import com.leafoct.myrubbishclassify.R;

public class fragment_set extends Fragment {
    public static Fragment newInstance(int i){
        fragment_set f=new fragment_set(i);
        return  f;
    }
    private View v;
    private int type;
    public fragment_set(int i){
        type=i;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        switch (type){
            case 0:
                v=inflater.inflate(R.layout.fragment_recycable, container, false);
                break;
            case 1:
                v=inflater.inflate(R.layout.fragment_poison, container, false);
                break;
            case 2:
                v=inflater.inflate(R.layout.fragment_wet, container, false);
                break;
            case 3:
                v=inflater.inflate(R.layout.fragment_dry, container, false);
                Button enter=v.findViewById(R.id.enter_app);
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(getContext(), Login.class);
                        startActivity(i);
                        getActivity().finish();
                    }
                });
                break;
            case 4:
                v=inflater.inflate(R.layout.fragment_acadamic_me, container, false);
                break;
        }
        return v;
    }
}
