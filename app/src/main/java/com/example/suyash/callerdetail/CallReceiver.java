package com.example.suyash.callerdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by suyash on 6/14/18.
 */

public  class CallReceiver extends IncomingCallReceiver {
    Context context;

    @Override
    public void onCall(final Context context, String callerDetail, String callerName, Bitmap callerPic){
        Toast.makeText(context,"Incoming call: " + callerDetail,Toast.LENGTH_LONG);
        this.context = context;
        final Intent intent = new Intent(context,DialogBox.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("phoneNumber",callerDetail);
        intent.putExtra("name",callerName);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        callerPic.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        intent.putExtra("image",byteArray);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                context.startActivity(intent);
            }
        },2000);
    }
}
