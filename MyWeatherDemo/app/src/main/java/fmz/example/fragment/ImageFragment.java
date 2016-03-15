package fmz.example.fragment;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import fmz.example.R;
import fmz.example.presenter.FragmentPresenter;
import fmz.example.utils.Setting;
import fmz.example.view.ImagesView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by zzk on 15/11/28.
 */
public class ImageFragment extends FragmentPresenter<ImagesView> {

    @Override
    protected void initData() {   //不是懒加载
        super.initData();
        getTitles(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        Setting mSetting = Setting.getInstance();
        if (mSetting.getInt("刷新", 1) != 1) {
            getTitles(getActivity());
            mSetting.putInt("刷新",1);
            Log.i("fmz", "正在刷新标题");
        }
    }

    private void getTitles(Context context) {
        Setting mSetting = Setting.getInstance();
        String city1 = mSetting.getString("城市1", "合肥");
        String city2 = mSetting.getString("城市2", "北京");
        String city3 = mSetting.getString("城市3", "上海");
        String city4 = mSetting.getString("城市4", "阜阳");
        String[] citys = {city1, city2, city3, city4};
        Observable.just(citys)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String[]>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String[] strings) {
                        mView.initViewPager(strings, getFragmentManager());
                    }
                });
        /* Observable.just(R.array.images_category_list)
                .map(new Func1<Integer, String[]>() {

                    @Override
                    public String[] call(Integer integer) {
                        return context.getResources().getStringArray(R.array.images_category_list);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String[]>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String[] strings) {
                        mView.initViewPager(strings, getFragmentManager());
                    }
                });*/
    }
}
