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
import fmz.example.helper.EventHelper;
import fmz.example.utils.Setting;

/**
 * Created by Administrator on 2016/3/8.
 */
public class SettingView extends ViewImpl{
    @Bind(R.id.Setting_button1)
    Button addcity1;
    @Bind(R.id.Setting_button2)
    Button addcity2;
    @Bind(R.id.Setting_button3)
    Button addcity3;
    @Bind(R.id.Setting_button4)
    Button addcity4;
    @Bind(R.id.Setting_button_del)
    Button delcity;

    private Setting setting;

    @Override
    public int getLayoutId() {
        return R.layout.settingactivity;
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        EventHelper.click(mPresenter,addcity1, addcity2, addcity3, addcity4, delcity);
//        String[] abc =getResources().getStringArray(R.array.images_category_list);
          /*addcity.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if(editText.equals(null)){
                      Toast.makeText(EApplication.mAppContext,"请输入城市名",Toast.LENGTH_SHORT);
                  }
                  mSetting.putString("城市1",editText.getText().toString().trim());
                  Toast.makeText(EApplication.mAppContext,"更改成功",Toast.LENGTH_SHORT);
              }
          });*/
    }

    @Override
    public void destroy() {

    }


}
