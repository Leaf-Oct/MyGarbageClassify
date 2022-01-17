package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class ExamPage extends BaseActivity implements View.OnClickListener{
//    约定可回收1,有害2,湿垃圾3,干4
    private String formal_1[]=new String[]{"纸巾","面包","牛奶","手机","玻璃","书本","衣服","皮鞋","酒精","高锰酸钾",
            "雨伞","姜","饮料","冰箱","暖风机","CPU","洗衣粉","被子","鼠标","抱枕"};
    private byte answer_formal_1[]=new byte[]{4,3,1,1,1,1,1,1,2,2,
            1,3,1,1,1,1,4,1,4,1};
    private String formal_2[]=new String[]{"灯管","电池","核污水","汽油","猪","西瓜","汽车","桶","消毒液","盐酸",
            "电线","胶布","蟑螂","骨头","钢琴","数位板","小提琴","笔","主板","油漆"};
    private byte answer_formal_2[]=new byte[]{2,2,2,2,4,3,1,1,2,2,
            1,4,2,4,1,1,1,2,1,2};
    private String formal_3[]=new String[]{"屏幕","轮胎","扳手","锤子","一次性杯","唱片","钻石","竹笋","硬盘","石头",
            "泥土","甘蔗","牛排","螺丝","硬币","煤炭","蛋糕","平板","陶瓷","砖头"};
    private byte answer_formal_3[]=new byte[]{1,1,1,1,4,4,1,3,1,4,
            4,3,3,1,1,4,3,1,3,1};
    private String fox_spirit[]=new String[]{"忆梦锤","黑狐","苦情树","厄喙兽","虚空之泪","金晨曦","纯质阳炎","王权剑","万尘归宗","九转玄阴水",
            "棒棒糖","金人凤","沐天城","北山之心","御妖符","竹笛","剑鞘","天门","王权剑意","千里传送符"};
    private byte answer_fox[]=new byte[]{1,2,3,2,3,2,1,1,4,3,
            3,2,4,1,4,4,4,4,1,4};
    private String konan[]=new String[]{"足球","眼镜","滑板","子弹","伏特加","少年白给团","足球腰带","枪","麻醉剂","窃听器",
            "信号发生器","口香糖","领结","西装","纸牌","卧底","相机","鸽子","歌牌","木剑"};
    private byte answer_konan[]=new byte[]{1,4,1,1,1,2,4,4,2,4,
            4,3,1,1,1,2,1,3,1,4};
    private String touhou[]=new String[]{"灵梦的御币","灵梦的赛钱箱","神社","红魔馆","妖梦的半灵","西行妖","魔理沙的魔炮","爱丽丝的人偶","妖梦楼观剑","大鲶鱼",
            "天子的要石","彼岸花","八目鳗","咲夜的红茶","河童的超大人偶","翠香的酒壶","翠香的手链","幽幽子的扇子","紫老太jhvkjvddd的伞","小町的镰刀"};
    private byte answer_touhou[]=new byte[]{1,1,4,2,3,3,4,4,1,3,
            4,3,3,3,1,1,1,4,1,4};

    private TextView rubbish_name;
    private Button recycable,harmful,wet,dry;
    private int which_paper,index,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("来测验了还想跑?");
        setContentView(R.layout.activity_exam_page);
        which_paper=getIntent().getIntExtra("number",-1);
        index=0;score=0;
        rubbish_name=findViewById(R.id.rubbish_name);
        recycable=findViewById(R.id.choice_recycable);
        harmful=findViewById(R.id.choice_harmful);
        wet=findViewById(R.id.choice_wet);
        dry=findViewById(R.id.choice_dry);
        initial_question();
        recycable.setOnClickListener(this);
        harmful.setOnClickListener(this);
        wet.setOnClickListener(this);
        dry.setOnClickListener(this);
    }
    private void initial_question(){
        String f=null;
        switch (which_paper){
            case 0:
                f=formal_1[index];break;
            case 1:
                f=formal_2[index];break;
            case 2:
                f=formal_3[index];break;
            case 3:
                f=fox_spirit[index];break;
            case 4:
                f=konan[index];break;
            case 5:
                f=touhou[index];break;
        }
        rubbish_name.setText(f);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Toast.makeText(this,"来答题了还想跑?车门焊死~",Toast.LENGTH_SHORT).show();
        return true;
    }

    private void judge(byte answer){
        byte right_answer=-1;
        switch (which_paper){
            case 0:
                right_answer=answer_formal_1[index];break;
            case 1:
                right_answer=answer_formal_2[index];break;
            case 2:
                right_answer=answer_formal_3[index];break;
            case 3:
                right_answer=answer_fox[index];break;
            case 4:
                right_answer=answer_konan[index];break;
            case 5:
                right_answer=answer_touhou[index];break;
        }
        if(answer==right_answer){
            score+=5;
        }
        index++;
        if(index==20){
            Intent i=new Intent(ExamPage.this,ExamResult.class);
            i.putExtra("result",score);
            startActivity(i);
            finish();
        }
        else{
            initial_question();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choice_recycable:
                judge((byte) 1);
                break;
            case R.id.choice_harmful:
                judge((byte) 2);
                break;
            case R.id.choice_wet:
                judge((byte) 3);
                break;
            case R.id.choice_dry:
                judge((byte) 4);
                break;
        }
    }
}
