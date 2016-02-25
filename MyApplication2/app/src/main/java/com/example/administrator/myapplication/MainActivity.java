package com.example.administrator.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

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

        try {
            String url1 = "api.openweathermap.org/data/2.5/weather?q="+location.getText().toString()+",uk&appid=44db6a862fba0b067b1930da0d769e98";

            URL url = new URL("http://"+url1);
            System.out.println(url);
            new MyAsycTask().execute("http://"+url1);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}