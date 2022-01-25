package com.example.basicphonecall;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class serviceReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        
        try {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);



            if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)){
                //Toast.makeText(context,"Call incoming",Toast.LENGTH_LONG).show();
              String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
               // Toast.makeText(context,"Calling number is "+incomingNumber,Toast.LENGTH_LONG).show();
                startIntent(context,incomingNumber);



            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void startIntent(Context context,String phone){
        Intent i=new Intent(context,MainActivity.class);
        i.putExtra("phone",phone);
        context.startActivity(i);
    }
}
