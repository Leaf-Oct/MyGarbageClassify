package com.leafoct.myrubbishclassify;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.leafoct.myrubbishclassify.database.AccountDatabase;

public class Register extends AppCompatActivity{
    private Button register;
    private EditText account,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=findViewById(R.id.register_button);
        account=findViewById(R.id.register_account);
        password=findViewById(R.id.register_password);
        AccountDatabase ad=new AccountDatabase(this,"account_password.db",null,1);
        final SQLiteDatabase db=ad.getWritableDatabase();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.query("account",new String[]{"account"},"account=?",new String[]{account.getText().toString()},null,null,null);
                if(c.moveToFirst()){
//                    账号已存在
                    AlertDialog.Builder exist_account=new AlertDialog.Builder(Register.this);
                    exist_account.setTitle("注册失败");
                    exist_account.setMessage("该账户已存在");
                    exist_account.setCancelable(false);
                    exist_account.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    exist_account.show();
                    return;
                }
                ContentValues new_account=new ContentValues();
                new_account.put("account",account.getText().toString());
                new_account.put("password",password.getText().toString());
                db.insert("account",null,new_account);
                new_account.clear();
                AlertDialog.Builder exist_account=new AlertDialog.Builder(Register.this);
                exist_account.setTitle("注册成功");
                exist_account.setMessage("账户注册成功，返回登录页面");
                exist_account.setCancelable(false);
                exist_account.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                exist_account.show();
            }
        });
    }
}
