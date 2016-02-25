package com.example.administrator.myapplication;
        import java.net.URL;

        import org.json.JSONException;
        import org.json.JSONObject;


public class JSONUtil {

    public static WeatherBean getWeatherBean(String s){

        String jsonText = s;
        WeatherBean weather = new WeatherBean();
        if(!jsonText.equals(null)) {
            try {
                JSONObject weatherJSONObject = new JSONObject(jsonText);
                JSONObject sysJSONObject = weatherJSONObject.getJSONObject("sys");
                String country = sysJSONObject.getString("country");
                JSONObject mainJSONObject = weatherJSONObject.getJSONObject("main");
                int temperature = mainJSONObject.getInt("temp");
                int pressure = mainJSONObject.getInt("pressure");
                int humidity = mainJSONObject.getInt("humidity");

                weather.setCountry(country);
                weather.setTemperature(temperature);
                weather.setHumidity(humidity);
                weather.setPressure(pressure);
            } catch (JSONException e) {
                System.out.println("test");
                e.printStackTrace();
            }

            return weather;
        }
        else{
            return null;
        }
    }
}