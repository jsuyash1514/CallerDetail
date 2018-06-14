package com.example.suyash.callerdetail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

/**
 * Created by suyash on 6/14/18.
 */

public class IncomingCallReceiver extends BroadcastReceiver {
    String callerDetail;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")){
            callerDetail = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        }
        else
        {
            String state = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING) || state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
            {
                callerDetail = number;
                onCall(context,callerDetail);

            }

        }
    }

    public void onCall(Context context, String callerDetail){}
}




