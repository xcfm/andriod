package fmz.example.view;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import fmz.example.R;
import fmz.example.adapter.BaseAdapterHelper;
import fmz.example.adapter.SimpleRecyclerAdapter;
import fmz.example.base.EApplication;
import fmz.example.utils.Setting;

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
    @Bind(R.id.Setting_edittext)
    EditText editText;
    private Setting setting;
    @Override
    public int getLayoutId() {
        return R.layout.settingactivity;
    }
    @Override
    public void bindEvent() {
        super.bindEvent();
        Setting mSetting = Setting.getInstance();
//        String[] abc =getResources().getStringArray(R.array.images_category_list);
          addcity.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(editText.equals(null)){
                      Toast.makeText(EApplication.mAppContext,"请输入城市名",Toast.LENGTH_SHORT);
                  }
                  mSetting.putString("城市",editText.getText().toString().trim());
              }
          });
        delcity.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  mSetting.clearcity();
              }
          });
    }
    @Override
    public void destroy() {

    }
}
