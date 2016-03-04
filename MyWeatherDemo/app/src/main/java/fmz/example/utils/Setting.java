package fmz.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

import fmz.example.base.EApplication;

/**
 * Created by Administrator on 2016/3/4.
 */
public class Setting {
    private static Setting sInstance;
    private SharedPreferences mPrefs;

    public static Setting getInstance() {
        if (sInstance == null) {
            sInstance = new Setting(EApplication.mAppContext);
        }
        return sInstance;
    }
    private Setting(Context context) {
        mPrefs = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
        //mPrefs.edit().putInt(CHANGE_ICONS, 1).apply();
    }

    public Setting putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return sInstance.mPrefs.getInt(key, defValue);
    }

}
