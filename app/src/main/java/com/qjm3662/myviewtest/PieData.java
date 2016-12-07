package com.qjm3662.myviewtest;

import android.support.annotation.NonNull;

/**
 * Created by tanshunwang on 2016/12/7 0007.
 */

public class PieData {
    //用户关心数据
    private String name;        //名字
    private float value;        //数值
    private float percentage;   //百分比

    //用户不关心数据
    private int color = 0;      //颜色
    private float angle = 0;    //角度

    public PieData(@NonNull String name, float value) {
        this.value = value;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", color=" + color +
                ", angle=" + angle +
                '}';
    }
}