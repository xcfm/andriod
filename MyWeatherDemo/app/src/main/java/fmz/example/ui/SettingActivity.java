package fmz.example.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import fmz.example.R;
import fmz.example.base.BaseActivity;
import fmz.example.presenter.ActivityPresenter;

/**
 * Created by Administrator on 2016/3/6.
 */
public class SettingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.settingactivity);
    }
}
