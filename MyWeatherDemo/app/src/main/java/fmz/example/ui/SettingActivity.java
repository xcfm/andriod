package fmz.example.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import fmz.example.R;
import fmz.example.adapter.BaseAdapterHelper;
import fmz.example.adapter.SimpleRecyclerAdapter;
import fmz.example.base.BaseActivity;
import fmz.example.base.EApplication;
import fmz.example.presenter.ActivityPresenter;
import fmz.example.utils.Setting;
import fmz.example.view.PersonalView;
import fmz.example.view.SettingView;

/**
 * Created by Administrator on 2016/3/6.
 */
public class SettingActivity extends ActivityPresenter<SettingView> implements View.OnClickListener {
    @Bind(R.id.Setting_edittext1)
    EditText editText1;
    @Bind(R.id.Setting_edittext2)
    EditText editText2;
    @Bind(R.id.Setting_edittext3)
    EditText editText3;
    @Bind(R.id.Setting_edittext4)
    EditText editText4;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        Setting mSetting = Setting.getInstance();
        switch (v.getId()) {
            case R.id.Setting_button1:
                editText1 = (EditText) findViewById(R.id.Setting_edittext1);
                String city = editText1.getText().toString().trim();
                if (city.equals("")) {
                    Snackbar.make(mView.getRootView(), "点击了Setting", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                mSetting.putString("城市1", editText1.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                //Snackbar.make(mView.getRootView(), "更改成功", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case R.id.Setting_button2:
                editText2 = (EditText) findViewById(R.id.Setting_edittext1);
                city = editText2.getText().toString().trim();
                if (city.equals("")) {
                    Snackbar.make(mView.getRootView(), "点击了Setting", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                mSetting.putString("城市2", editText2.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Setting_button3:
                editText3 = (EditText) findViewById(R.id.Setting_edittext1);
                city = editText3.getText().toString().trim();
                if (city.equals("")) {
                    Snackbar.make(mView.getRootView(), "点击了Setting", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                mSetting.putString("城市3", editText3.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Setting_button4:
                editText4 = (EditText) findViewById(R.id.Setting_edittext1);
                city = editText4.getText().toString().trim();
                if (city.equals("")) {
                    Snackbar.make(mView.getRootView(), "点击了Setting", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
                mSetting.putString("城市4", editText4.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "更改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Setting_button_del:
                mSetting.clearcity();
                Toast.makeText(getApplicationContext(), "清除成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

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
