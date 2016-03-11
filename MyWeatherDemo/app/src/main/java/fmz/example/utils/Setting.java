package fmz.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

import fmz.example.R;
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

    public Setting putString(String key,String value){
        mPrefs.edit().putString(key,value).apply();
        return this;
    }
    public String getString(String key, String defValue) {
        return sInstance.mPrefs.getString(key, defValue);
    }
    public Setting clearcity(){
        mPrefs.edit().clear().apply();
       Setting mSetting = Setting.getInstance();
        mSetting.putInt("晴", R.mipmap.type_one_sunny);
        mSetting.putInt("阴", R.mipmap.type_one_cloudy);
        mSetting.putInt("多云", R.mipmap.type_one_cloudy);
        mSetting.putInt("少云", R.mipmap.type_one_cloudy);
        mSetting.putInt("晴间多云", R.mipmap.type_one_cloudytosunny);
        mSetting.putInt("小雨", R.mipmap.type_one_light_rain);
        mSetting.putInt("中雨", R.mipmap.type_one_middle_rain);
        mSetting.putInt("大雨", R.mipmap.type_one_heavy_rain);
        mSetting.putInt("阵雨", R.mipmap.type_one_thunderstorm);
        mSetting.putInt("雷阵雨", R.mipmap.type_one_thunderstorm);
        mSetting.putInt("霾", R.mipmap.type_one_fog);
        mSetting.putInt("雾", R.mipmap.type_one_fog);
        mSetting.putInt("阵雪",R.mipmap.type_one_lsnow);
        return this;
    }

}
