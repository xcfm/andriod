package fmz.example.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import fmz.example.R;
import fmz.example.presenter.ActivityPresenter;
import fmz.example.utils.BitmapUtil;
import fmz.example.utils.Setting;
import fmz.example.view.MainView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class MainActivity extends ActivityPresenter<MainView>
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void initData() {
        super.initData();
        getBitmap();
        mView.setNavigationItemSelected(getSupportFragmentManager(), R.id.nav_camera);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mView.setOptionsItemSelected(item.getItemId());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return mView.setNavigationItemSelected(getSupportFragmentManager(), item.getItemId());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Uri uri = Uri.parse("https://github.com/xcfm/andriod");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
                break;
            case R.id.imageView:
                Intent intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (mView.closeDrawer())
            return;
        super.onBackPressed();
    }

    private void getBitmap() {
        /*Observable.just(R.mipmap.avator)
                .map(new Func1<Integer, Bitmap>() {
                    @Override
                    public Bitmap call(Integer integer) {
                        return BitmapUtil.matrixBitmap(MainActivity.this, integer);
                    }
                })
                .map(new Func1<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap call(Bitmap bitmap) {
                        return BitmapUtil.blurBitmap(MainActivity.this, bitmap, 15.5f);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        mView.setAvator(bitmap);
                    }
                });*/
        this.setStatusBarColor(R.color.light_grey);
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
    }
}
