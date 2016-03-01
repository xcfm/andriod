package com.example.administrator.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyAdapter extends BaseAdapter{
private LayoutInflater mInflater;
    private List<Bean> beans;
    public MyAdapter(Context context, List<Bean> beans) {
         mInflater=LayoutInflater.from(context);
            this.beans =beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder hoder = null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item,parent,false);
            hoder =new ViewHoder();
            hoder.textView1= (TextView) convertView.findViewById(R.id.textView_1);
            hoder.textView2= (TextView) convertView.findViewById(R.id.textView_2);
            hoder.textView3= (TextView) convertView.findViewById(R.id.textView_3);
            convertView.setTag(hoder);
        }
        else {
            hoder= (ViewHoder) convertView.getTag();
        }
        Bean bean =beans.get(position);
        hoder.textView1.setText("日期："+bean.getDate());
        hoder.textView2.setText("温度："+bean.getTemperature());
        hoder.textView3.setText("天气:"+bean.getWeather());

        return convertView;
    }
    private class ViewHoder{
        TextView textView1;
        TextView textView2;
        TextView textView3;

    }
}
