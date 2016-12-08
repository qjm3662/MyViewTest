package com.qjm3662.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewTestActivity extends AppCompatActivity {

    private CheckView checkView;
    private Button btn_check;
    private Button btn_unCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        checkView = (CheckView) findViewById(R.id.checkView);
//        checkView.setAnimDuration(2000);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_unCheck = (Button) findViewById(R.id.btn_unCheck);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.check();
            }
        });
        btn_unCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.unCheck();
            }
        });
    }
}
