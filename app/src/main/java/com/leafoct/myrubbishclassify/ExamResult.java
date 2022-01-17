package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExamResult extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exam_result);
        int score= getIntent().getIntExtra("result",-1);
        TextView back=findViewById(R.id.goback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView show_score=findViewById(R.id.score);
        TextView comment=findViewById(R.id.comment);
        if(score==-1){
            show_score.setText("啊哦~");
            comment.setText("好像有点bug哦");
            return ;
        }
        show_score.setText(new Integer(score).toString());
        if(score>80){
            comment.setText("还不错呀");
        }
        else if(score>60){
            comment.setText("可以可以");
        }
        else{
            comment.setText("丢人，你退学吧");
        }
    }
}
