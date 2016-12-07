package com.qjm3662.myviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PieData> mData = new ArrayList<PieData>();
    private PieView pieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData.add(new PieData("爱情", 90));
        mData.add(new PieData("面包", 60));
        mData.add(new PieData("生活", 60));
        pieView = (PieView) findViewById(R.id.pieView);
        pieView.setData(mData);
//        for(PieData p : mData){
//            System.out.println(p.toString());
//        }
    }
}
