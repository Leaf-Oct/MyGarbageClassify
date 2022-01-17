package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.leafoct.myrubbishclassify.database.AccountDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private TextView forget_password;
    private CheckBox remember_password;
    private EditText input_account,input_password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_account=findViewById(R.id.account);
        input_password=findViewById(R.id.password);
        remember_password=findViewById(R.id.remember_password);
        SharedPreferences save_password=getSharedPreferences("save",MODE_PRIVATE);
        if(save_password.getBoolean("saved",false)){
            input_account.setText(save_password.getString("acc",""));
            input_password.setText(save_password.getString("passwd",""));
            remember_password.setChecked(true);
        }
        initial_forget();
        initial_login();
        initial_register();
    }
    private void initial_forget(){
        forget_password=findViewById(R.id.forget_password);
        forget_password.setOnClickListener(this);
    }
    private void initial_register(){
        register=findViewById(R.id.register);
        register.setOnClickListener(this);
    }
    private void initial_login(){
        login=findViewById(R.id.login_button);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.forget_password:
                AlertDialog.Builder default_password_root=new AlertDialog.Builder(Login.this);
                default_password_root.setTitle("忘记密码？");
                default_password_root.setMessage("要么默认账户密码,root和root。要么再注册一个吧");
                default_password_root.setCancelable(false);
                default_password_root.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                default_password_root.show();
                break;
            case R.id.register:
                Intent i=new Intent(this,Register.class);
                startActivity(i);
                break;
            case R.id.login_button:
                SharedPreferences.Editor spe=getSharedPreferences("save",MODE_PRIVATE).edit();
                if(remember_password.isChecked()){
//                    存密码
                    spe.putBoolean("saved",true);
                    spe.putString("acc",input_account.getText().toString());
                    spe.putString("passwd",input_password.getText().toString());
                    spe.apply();
                    spe.clear();
                }
                else{
                    spe.putBoolean("saved",false);
                    spe.putString("acc","");
                    spe.putString("passwd","");
                    spe.apply();
                    spe.clear();
                }
                String input_user_name_string=input_account.getText().toString();
                String input_password_string=input_password.getText().toString();
                AccountDatabase ad=new AccountDatabase(this,"account_password.db",null,1);
                final SQLiteDatabase db=ad.getWritableDatabase();
                Cursor c=db.query("account",new String[]{"password"},"account=?",new String[]{input_user_name_string},null,null,null);
                if(c.moveToFirst()){
                    String right_password=c.getString(c.getColumnIndex("password"));
                    if(right_password.equals(input_password_string)){
                        Intent to_main=new Intent(this,Main.class);
                        startActivity(to_main);
                    }
                    else{
                        AlertDialog.Builder exist_account=new AlertDialog.Builder(Login.this);
                        exist_account.setTitle("登录失败");
                        exist_account.setMessage("密码错误");
                        exist_account.setCancelable(false);
                        exist_account.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        exist_account.show();
                    }
                }else{
                    AlertDialog.Builder exist_account=new AlertDialog.Builder(Login.this);
                    exist_account.setTitle("???");
                    exist_account.setMessage("账号不存在！");
                    exist_account.setCancelable(false);
                    exist_account.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    exist_account.show();
                }
                break;
        }
    }
}
