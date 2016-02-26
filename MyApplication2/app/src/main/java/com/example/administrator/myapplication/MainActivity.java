package com.example.administrator.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.myapplication.retrofit.APIService;
import com.example.administrator.myapplication.retrofit.Contributor;
import com.example.administrator.myapplication.retrofit.GitHub;
import com.example.administrator.myapplication.retrofit.OpenWeather;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    String apiKey = "c26d0b090a494726ab3957852cffa60b";
    String baseUrl = "http://apis.haoservice.com/";
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
        }
    class  MyAsycTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.print("异步线程开始");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            WeatherBean weatherBean = JSONUtil.getWeatherBean(s);
            country.setText(weatherBean.getCountry());
            humidity.setText(weatherBean.getHumidity() + "");
            pressure.setText(weatherBean.getPressure() + "");
            temperature.setText((weatherBean.getTemperature()-273) + "");
            System.out.println("test2");
        }



        @Override
        protected String doInBackground(String... params) {
            InputStream is =null;
            BufferedReader in = null;
            String jsonText="";
            URL url= null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();

                is = conn.getInputStream();
                in = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while((line = in.readLine()) != null){
                    jsonText += line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    in.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return jsonText;
        }
    }

    public void open(View view) {

        try {
            String url1 = "api.openweathermap.org/data/2.5/weather?q="+location.getText().toString()+",uk&appid=44db6a862fba0b067b1930da0d769e98";
            URL url = new URL("http://"+url1);
            System.out.println(url);
            new MyAsycTask().execute("http://"+url1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void maladroit(View view){
        get();
      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        GitHub gitHubService = retrofit.create(GitHub.class);
        Call<List<Contributor>> call = gitHubService.contributors("square", "retrofit");
       // Call<List<Contributor>> call1 = call.clone();
// 5. 请求网络，异步
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Response<List<Contributor>> response, Retrofit retrofit) {
                String a= response.body().get(0).login;
                int b=response.body().get(0).contributions;
                temperature.setText(a);
                humidity.setText(b+"");
            }*/

       /* try{
            Response<List<Contributor>> response = call.execute(); // 同步
            Log.d("mytag", "response:" + response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    private void get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
       String city =location.getText().toString();
        //  String city ="合肥";
        service.loadeather(city, apiKey).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Response<Weather> response, Retrofit retrofit) {
                if (response.body() != null) {
                    Weather weather = response.body();
                    Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
                    country.setText(todayEntiry.getCity());
                    temperature.setText(weather.getResult().getSk().getTemp());
                    humidity.setText(todayEntiry.getTemperature());
                    pressure.setText(todayEntiry.getWeather());
                    Log.i("fmz", "onResponse: 城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature());
                } else {
                    Log.e("fmz", "onResponse: body==null");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("fmz", "onFailure: ", t);
            }
        });
    }
}