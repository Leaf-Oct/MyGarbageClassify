package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.leafoct.myrubbishclassify.database.AccountDatabase;

import static java.lang.Thread.sleep;


public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        ImageView sp=findViewById(R.id.splash);
        sp.setImageBitmap(UserProtocal.readBitMap(this,R.drawable.splash));
//        用SP判断是否是第一次访问
        if(getSharedPreferences("first",MODE_PRIVATE).getString("f","false").equals("false")){
            SharedPreferences.Editor spe=getSharedPreferences("first",MODE_PRIVATE).edit();
            spe.putString("f","true");
            spe.apply();
            Intent toLead=new Intent(this,FirstLead.class);
//            初始化数据库
            new AccountDatabase(this,"account_password.db",null,1).getReadableDatabase();
            startActivity(toLead);
            finish();
        }
        else{
            Thread wait_splash=new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(1000);
                        Intent to_login=new Intent(Splash.this,Login.class);
                        startActivity(to_login);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            wait_splash.start();
        }
    }
}
