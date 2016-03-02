package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myapplication.retrofit.APIService;
import com.example.administrator.myapplication.untils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    String apiKey = "c26d0b090a494726ab3957852cffa60b";
    String baseUrl = "http://apis.haoservice.com/";
    private ListView listView;
    private List<Bean> Futures;
    private EditText location, country, temperature, humidity, pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = (EditText) findViewById(R.id.editText1);
        country = (EditText) findViewById(R.id.editText2);
        temperature = (EditText) findViewById(R.id.editText3);
        humidity = (EditText) findViewById(R.id.editText4);
        pressure = (EditText) findViewById(R.id.editText5);
        listView = (ListView) findViewById(R.id.listView);
    }


    public void open(View view) {
        getByRxJava();
       /* try {
            String url1 = "api.openweathermap.org/data/2.5/weather?q="+location.getText().toString()+",uk&appid=44db6a862fba0b067b1930da0d769e98";
            URL url = new URL("http://"+url1);
            System.out.println(url);
            new MyAsycTask().execute("http://"+url1);


        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void maladroit(View view) {
        get();

    }

    private void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        String city = location.getText().toString();
        //  String city ="合肥";
        Observable<Weather> observable = service.getWeatherData(city, apiKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Weather, Boolean>() {
                    @Override
                    public Boolean call(Weather weather) {
                        if (weather.getReason().equals("成功")) {
                            Toast.makeText(getApplicationContext(),
                                    "获取完毕",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "获取失败",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
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
                        Log.e("fmz", "onError: ", e);
                        Toast.makeText(getApplicationContext(),
                                "Error:" + e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                        Log.i("fmz", "onNext: 城市:" + weather.getResult().getFuture().get(0).getTemperature() + " 温度:" + todayEntiry.getTemperature());

                        //Toast.makeText(MainActivity.this, "明天:" + weather.getResult().getFuture().get(0).getWeek() + " 温度:" + weather.getResult().getFuture().get(0).getTemperature(), Toast.LENGTH_SHORT).show();
                        Futures = new ArrayList<>();
                        for (int i = 0; i < 6; i++) {
                            Bean bean = new Bean(weather, i);
                            Futures.add(bean);
                        }
                        //MyAdapter2 adapter2 =new MyAdapter2(MainActivity.this,Futures,R.layout.item);
                        CommonAdapter adapter2 = new CommonAdapter<Bean>(MainActivity.this,Futures,R.layout.item) {
                            @Override
                            public void convert(ViewHolder viewHolder, Bean item) {
                                viewHolder.setText(R.id.textView_1,item.getWeek())
                                        .setText(R.id.textView_2,item.getWeather())
                                        .setText(R.id.textView_3,item.getTemperature());
                            }
                        };
                        listView.setAdapter(adapter2);
                        country.setText(todayEntiry.getCity());
                        temperature.setText(weather.getResult().getSk().getTemp());
                        humidity.setText(todayEntiry.getTemperature());
                        pressure.setText(todayEntiry.getWeather());

                    }
                });

    }

    private void getByRxJava() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        String city = location.getText().toString();
        //  String city ="合肥";
        Observable<Weather> observable = service.getWeatherData(city, apiKey);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Weather, Boolean>() {
                    @Override
                    public Boolean call(Weather weather) {
                        if (weather.getReason().equals("成功")) {
                            Toast.makeText(getApplicationContext(),
                                    "获取完毕",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "获取失败",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
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
                        Log.e("fmz", "onError: ", e);
                        Toast.makeText(getApplicationContext(),
                                "Error:" + e.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(Weather weather) {
                        Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                        Log.i("fmz", "onNext: 城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature());
                        Toast.makeText(MainActivity.this, "明天:" + weather.getResult().getFuture().get(0).getWeek() + " 温度:" + weather.getResult().getFuture().get(0).getTemperature(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}