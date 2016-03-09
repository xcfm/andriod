package fmz.example.view;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import fmz.example.R;
import fmz.example.adapter.BaseAdapterHelper;
import fmz.example.adapter.SimpleRecyclerAdapter;

/**
 * Created by Administrator on 2016/3/8.
 */
public class SettingView extends ViewImpl {
    @Bind(R.id.Setting_button1)
    Button addcity;
    @Bind(R.id.Setting_button2)
     Button delcity;
    @Bind(R.id.Setting_citylist)
     RecyclerView listView;
    @Override
    public int getLayoutId() {
        return R.layout.settingactivity;
    }
    @Override
    public void bindEvent() {
        super.bindEvent();
//        String[] abc =getResources().getStringArray(R.array.images_category_list);

    }
    @Override
    public void destroy() {

    }
}
