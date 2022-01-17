package com.leafoct.myrubbishclassify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ProblemFeedback extends BaseActivity {
    private EditText feedback_content;
    private Button confirm;
    private TextView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_problem_feedback);
        feedback_content=(EditText)findViewById(R.id.feedback_text);
        confirm=(Button) findViewById(R.id.confirm_feedback);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(ProblemFeedback.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ProblemFeedback.this,new String[]{Manifest.permission.READ_CONTACTS},1);
                    return;
                }
                else{
                    send_message();
                }
            }
        });
        goback=findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void send_message(){
        Cursor c=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        if(c.moveToFirst()){
            int number=c.getCount();
            Random r=new Random();
            int time=r.nextInt(number);
            while(time--!=0){
                c.moveToNext();
            }
            String phone_number=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String text=feedback_content.getText().toString();
            Intent i=new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone_number));
            i.putExtra("sms_body",text);
            startActivity(i);

        }else{
            Toast.makeText(getApplicationContext(),"挺孤单的,手机里一个联系人都没有",Toast.LENGTH_SHORT);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    send_message();
                }
                else{
                    Toast.makeText(this,"不给权限是吧,我直接耍流氓",Toast.LENGTH_LONG).show();
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }
}
