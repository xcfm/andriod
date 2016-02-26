package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends Activity {

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
            temperature.setText(weatherBean.getTemperature() + "");
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();
        OpenWeather openWeather = retrofit.create(OpenWeather.class);
        String a=location.getText().toString();
        String api ="44db6a862fba0b067b1930da0d769e98";
        //Call<JSONObject> call = openWeather.weather("london","44db6a862fba0b067b1930da0d769e98");
        Call<JSONObject> call =openWeather.weather(a,api);
        // Call<List<Contributor>> call1 = call.clone();
// 5. 请求网络，异步
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Response<JSONObject> response, Retrofit retrofit) {
                System.out.print(response.body().toString()+"huoqudaoshuju");
                WeatherBean weatherBean = JSONUtil.getWeatherBean(response.body().toString());
                country.setText(weatherBean.getCountry());
                humidity.setText(weatherBean.getHumidity() + "");
                pressure.setText(weatherBean.getPressure() + "");
                temperature.setText(weatherBean.getTemperature() + "");
                System.out.println("test2");
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

       /* try {
            String url1 = "api.openweathermap.org/data/2.5/weather?q="+location.getText().toString()+",uk&appid=44db6a862fba0b067b1930da0d769e98";
            URL url = new URL("http://"+url1);
            System.out.println(url);
            new MyAsycTask().execute("http://"+url1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}