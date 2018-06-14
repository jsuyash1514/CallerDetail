package com.example.suyash.callerdetail;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by suyash on 6/14/18.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    String callerDetail;
    String callername ;
    Bitmap callerPic;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")){
            callerDetail = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            callername = getContactName(callerDetail,context);
            callerPic = retrieveContactPhoto(context,callerDetail);
        }
        else
        {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING) || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                callerDetail = number;
                callername = getContactName(callerDetail,context);
                callerPic = retrieveContactPhoto(context,callerDetail);
                onCall(context,callerDetail, callername, callerPic);

            }

        }
    }

    public void onCall(Context context, String callerDetail, String callername, Bitmap callerPic){}

    public String getContactName(final String phoneNumber, Context context)
    {
        Uri uri=Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));

        String[] strings = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};

        String contactName="";
        Cursor cursor=context.getContentResolver().query(uri,strings,null,null,null);

        if (cursor != null) {
            if(cursor.moveToFirst()) {
                contactName=cursor.getString(0);
            }
            cursor.close();
        }

        return contactName;
    }

    public static Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor = contentResolver.query(uri, projection, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher_foreground);

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }
}




