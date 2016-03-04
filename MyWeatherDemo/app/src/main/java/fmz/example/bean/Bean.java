package fmz.example.bean;

/**
 * Created by Administrator on 2016/3/1.
 */
public class Bean{
    public Bean(Weather weather,int postion) {
        this.temperature = weather.getResult().getFuture().get(postion).getTemperature();
        this.weather = weather.getResult().getFuture().get(postion).getWeather();
        this.date = weather.getResult().getFuture().get(postion).getDate();
        this.week = weather.getResult().getFuture().get(postion).getWeek();
    }

    private String temperature;
    private String weather;
    private String fa;
    private String fb;
    private String wind;
    private String week;
    private String date;

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeather() {
        return weather;
    }

    public String getFa() {
        return fa;
    }

    public String getFb() {
        return fb;
    }

    public String getWind() {
        return wind;
    }

    public String getWeek() {
        return week;
    }

    public String getDate() {
        return date;
    }
}
