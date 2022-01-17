package com.leafoct.myrubbishclassify.mainFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.leafoct.myrubbishclassify.AboutItemAdapter;
import com.leafoct.myrubbishclassify.AboutListItem;
import com.leafoct.myrubbishclassify.ContactMe;
import com.leafoct.myrubbishclassify.ProblemFeedback;
import com.leafoct.myrubbishclassify.R;
import com.leafoct.myrubbishclassify.UserProtocal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class mine extends Fragment {


    public mine() {
    }

    private ArrayList<AboutListItem> items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mine, container, false);
        items=new ArrayList<>();
        initial_item();
        AboutItemAdapter adapter =new AboutItemAdapter(getContext(),R.layout.about_item,items);
        ListView list=v.findViewById(R.id.about_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getActivity(), ProblemFeedback.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), ContactMe.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), UserProtocal.class));
                        break;
                    case 3:
                        Toast.makeText(getContext(),"已是最新(zhōng)版本",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        return v;
    }
    private void initial_item(){
        AboutListItem a1=new AboutListItem("bug反馈",R.drawable.src_assets_qaicon);
        AboutListItem a2=new AboutListItem("联系官方",R.drawable.src_assets_weicon);
        AboutListItem a3=new AboutListItem("用户协议",R.drawable.src_assets_protocolicon);
        AboutListItem a4=new AboutListItem("当前版本",R.drawable.src_assets_versionicon);
        items.add(a1);
        items.add(a2);
        items.add(a3);
        items.add(a4);
    }
}
