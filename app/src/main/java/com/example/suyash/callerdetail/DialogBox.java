package com.example.suyash.callerdetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogBox extends AppCompatActivity {

    TextView tv_callerName, tv_callerNumber;
    String callerDetail,callerName = "";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_box);

        tv_callerName = (TextView)findViewById(R.id.callerName);
        tv_callerNumber  = (TextView)findViewById(R.id.callerNumber);
        button = (Button)findViewById(R.id.button_OK);

        Display display = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();


        layoutParams.x = 100;
        layoutParams.height = 700;
        layoutParams.width = display.getWidth();
        layoutParams.y = 200;


        this.getWindow().setAttributes(layoutParams);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bitmap);

        callerDetail = getIntent().getExtras().getString("phoneNumber");
        callerName = getIntent().getExtras().getString("name");
        tv_callerName.setText(callerName );
        tv_callerNumber.setText(callerDetail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox.this.finish();
                System.exit(0);
            }
        });



    }
}
