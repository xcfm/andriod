package com.example.administrator.myapplication;

import android.content.Context;

import com.example.administrator.myapplication.untils.CommonAdapter;
import com.example.administrator.myapplication.untils.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/2.
 */
public class MyAdapter2 extends CommonAdapter<Bean>{
    public MyAdapter2(Context context, List<Bean> datas, int itemLayoutResId) {
        super(context, datas, itemLayoutResId);
    }

    @Override
    public void convert(ViewHolder viewHolder, Bean item) {
        viewHolder.setText(R.id.textView_1,item.getWeek())
                .setText(R.id.textView_2,item.getWeather())
                .setText(R.id.textView_3,item.getTemperature());
    }
}
