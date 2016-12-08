package com.qjm3662.myviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PieData> mData = new ArrayList<PieData>();
    private PieView pieView;
    private Button btnStartViewTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData.add(new PieData("爱情", 90));
        mData.add(new PieData("面包", 60));
        mData.add(new PieData("生活", 60));
        pieView = (PieView) findViewById(R.id.pieView);
        pieView.setData(mData);
        btnStartViewTest = (Button) findViewById(R.id.btn_startViewTest);
        btnStartViewTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTestActivity.class));
            }
        });
//        for(PieData p : mData){
//            System.out.println(p.toString());
//        }
    }
}
