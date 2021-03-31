package com.example.smsone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText phoneNumber;
    private EditText smsBody;
    private Button smsManagerBtn;
    private Button smsSentIntent;
    private Button smsSend;
    private TextView headingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.phoneNumber);
        smsBody = findViewById(R.id.smsBody);
        smsManagerBtn = findViewById(R.id.smsManager);
        smsSentIntent = findViewById(R.id.smsIntent);
        smsSend = findViewById(R.id.sendSms);
        headingText = findViewById(R.id.headingText);



    }
    public void sendSmsByManager(View view){
        try{
            //get the default instance of the smsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber.getText().toString(),
                    null,
                    smsBody.getText().toString(),
                    null,
                    null);
            Toast.makeText(getApplicationContext(),"Your sms has been sent",
                    Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Sms failed",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void sendSmsIntent(View view){


        Intent intent = new Intent(Intent.ACTION_VIEW);
        //prompt the sms clients
        intent.setType("vnd.android-dir/mms-sms");
        //number fields
        intent.putExtra("address", phoneNumber.getText().toString());
        intent.putExtra("sms_body", smsBody.getText().toString());

        try{
            startActivity(intent);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Sms can't send",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }

    public void sendSmsThree(View view){
        //and the phone number in the data
        Uri uri = Uri.parse("smsto:"+phoneNumber.getText().toString());

        Intent intent2 = new Intent(Intent.ACTION_SENDTO, uri);
        // add the message at the sms body extra field
        intent2.putExtra("sms_body",smsBody.getText().toString());

        try{
            startActivity(intent2);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Sms can't send",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}