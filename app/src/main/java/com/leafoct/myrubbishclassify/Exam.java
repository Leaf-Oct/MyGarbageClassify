package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Exam extends BaseActivity {
    private ListView question_list;
    private String[] question_item=new String[]{"正经测试题1","正经测试题2","正经测试题3","狐妖私货题","柯南私货题","东方私货题"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exam);
        initial_list();
        TextView back=findViewById(R.id.goback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initial_list(){
        question_list=findViewById(R.id.question_list);
        ArrayAdapter<String> list_adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,question_item);
        question_list.setAdapter(list_adapter);
        question_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //from zero
                Intent to_exam=new Intent(Exam.this,ExamPage.class);
                to_exam.putExtra("number",i);
                startActivity(to_exam);
            }
        });
    }

}
