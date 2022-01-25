package com.example.basicphonecall;

import static android.content.ContentValues.TAG;
import static android.content.Intent.ACTION_DIAL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


private static final int REQUEST_CALL= 1;
private EditText mEditTextNumber;
String phone="TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextNumber=findViewById(R.id.Editphonenumber);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            phone=extras.getString("phone");
            mEditTextNumber.setText(phone);

           // makePhoneCall();
        }



        ImageView imagecall=findViewById(R.id.icon_call);
        imagecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               makePhoneCall();
                //getPhoneContacts();

            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){


            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_PHONE_STATE},REQUEST_CALL);
        }
    }
    @SuppressLint("Range")


    private void makePhoneCall(){
        String number=mEditTextNumber.getText().toString();
        // user typed anything in the text area,remove spaces

        if(number.trim().length()>0){
            //if the permission is not granted

            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
           // request the permission to call

                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                String callstring;
               // String dial="tel:" +number;
               // Intent i= new Intent(Intent.ACTION_CALL,Uri.parse(dial));
               // startActivity(i);

              //  if(number.startsWith("*")&&(number.endsWith("#"))){
                 //  callstring=number.substring(0, number.length()-1);
                  // callstring=callstring+Uri.encode("#");
                   //Log.d("CALL TYPE-------->","USSD CALL");

                   callstring="*182*1*1*"+number+Uri.encode("#");
                Log.d("what is send as ussd: ",callstring);

                //}

                //else{
                  //  callstring= number;
                   // Log.d("CALL TYPE---------->","not a USSD CALL");
                   // Intent i= new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mEditTextNumber.getText().toString()));
                    // startActivity(i);
               // }
                Intent i= new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+callstring));
                startActivity(i);
            }


        }
        //if  user try to dial w/o input
        else{
            Toast.makeText(MainActivity.this,"Enter Phone Number",Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CALL){

            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
