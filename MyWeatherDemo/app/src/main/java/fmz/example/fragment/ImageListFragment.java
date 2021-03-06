package fmz.example.fragment;

import android.os.Bundle;
import android.util.Log;

import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.List;

import fmz.example.bean.Bean;
import fmz.example.bean.Weather;
import fmz.example.listener.APIService;
import fmz.example.presenter.FragmentPresenter;
import fmz.example.view.ImageListView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ImageListFragment extends FragmentPresenter<ImageListView> implements XRecyclerView.LoadingListener {

    boolean isRefresh = true;
    String keyword;
    int page = 0;

    public static ImageListFragment newInstance(String title) {
        ImageListFragment imageListFragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("keyword", title);
        imageListFragment.setArguments(bundle);//为了防止切换横竖屏时数据丢失
        return imageListFragment;
    }

    public void onResume() {
    super.onResume();

    }

    @Override
    protected void lazyData() {
        super.lazyData();
        mView.initViews(getActivity(), this);
        getData();
    }

    private void getData() {
        keyword = getArguments().getString("keyword");
        String apiKey = "da8122ba9f394b3d9ed02e3e49da74ea";
        //String apiKey = "c26d0b090a494726ab3957852cffa60b";
        String baseUrl = "http://apis.haoservice.com/";
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://image.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        HttpApi api = retrofit.create(HttpApi.class);*/
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        Observable<Weather> observable = service.getWeatherData(keyword, apiKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Weather, Boolean>() {
                    @Override
                    public Boolean call(Weather weather) {
                        return weather.getReason().equals("成功");
                    }
                })
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                        Log.i("fmz", "onCompleted: ");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Weather weather) {
                        Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                        Log.i("fmz", "onNext: 城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature());
                        ArrayList<Bean> Futures = new ArrayList<>();
                        ArrayList<Weather.ResultEntity.FutureEntity> abc = (ArrayList<Weather.ResultEntity.FutureEntity>) weather.getResult().getFuture();
                        int a = abc.size();
                        for (int i = 0; i <= a; i++) {
                            Bean bean = new Bean(weather, i);
                            Futures.add(bean);
                        }
                        if (isRefresh) {
                            mView.refreshListData(Futures);
                        }
                        //mView.addListData(Futures);
                    }
                });


    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        page = 0;
        getData();
    }

    @Override
    public void onLoadMore() {
        isRefresh = false;
        page++;
        getData();
    }

}
