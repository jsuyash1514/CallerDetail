package com.example.suyash.callerdetail;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

/**
 * Created by suyash on 6/14/18.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    String callerDetail;
    String callername;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")){
            callerDetail = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");

            callername = getContactName(callerDetail,context);


        }
        else
        {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING) || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                callerDetail = number;
                callername = getContactName(callerDetail, context);
                onCall(context,callerDetail, callername);

            }
        }
    }

    public void onCall(Context context, String callerDetail, String callername){}

    public String getContactName(final String phoneNumber, Context context)
    {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String[] strings = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="Unknown";
        Cursor cursor=context.getContentResolver().query(uri,strings,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }

}




