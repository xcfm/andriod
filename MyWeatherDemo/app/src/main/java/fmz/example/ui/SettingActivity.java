package fmz.example.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fmz.example.R;
import fmz.example.adapter.BaseAdapterHelper;
import fmz.example.adapter.SimpleRecyclerAdapter;
import fmz.example.base.BaseActivity;
import fmz.example.presenter.ActivityPresenter;
import fmz.example.view.PersonalView;
import fmz.example.view.SettingView;

/**
 * Created by Administrator on 2016/3/6.
 */
public class SettingActivity extends ActivityPresenter<SettingView>{

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

/*
public class SettingActivity extends Activity {
    private Button addcity;
    private Button delcity;
    private RecyclerView listView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.settingactivity);
        addcity = (Button) findViewById(R.id.Setting_button1);
        delcity = (Button) findViewById(R.id.Setting_button2);
        listView = (RecyclerView) findViewById(R.id.Setting_citylist);
        String[] abc =this.getResources().getStringArray(R.array.images_category_list);
        List<String> arrayList= Arrays.asList(abc);
        SimpleRecyclerAdapter<String> settingAdapter =new SimpleRecyclerAdapter<String>(this,arrayList,R.layout.settingsitem) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.setting_item,item);
            }
        };
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(settingAdapter);
    }

}
*/
