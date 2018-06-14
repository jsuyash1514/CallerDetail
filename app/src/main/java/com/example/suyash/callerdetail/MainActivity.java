package com.example.suyash.callerdetail;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    TextView textView;
    String callerDetail;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setFinishOnTouchOutside(false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        button = (Button)findViewById(R.id.button_OK);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();


            layoutParams.x = 100;
            layoutParams.height = 500;
            layoutParams.width = 2000;
            layoutParams.y = 200;


            this.getWindow().setAttributes(layoutParams);


        callerDetail = getIntent().getExtras().getString("phoneNumber");
        textView.setText("Call: " + callerDetail);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });



    }
}
