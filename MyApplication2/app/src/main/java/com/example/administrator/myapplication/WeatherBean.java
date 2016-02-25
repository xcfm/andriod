package com.example.administrator.myapplication;

 public class WeatherBean {

 private String country;
 private int Temperature;
 private int humidity;
 private int pressure;
 public String getCountry() {
 return country;
 }
 public void setCountry(String country) {
 this.country = country;
 }
 public int getTemperature() {
 return Temperature;
 }
 public void setTemperature(int temperature) {
 Temperature = temperature;
 }
 public int getHumidity() {
 return humidity;
 }
 public void setHumidity(int humidity) {
 this.humidity = humidity;
 }
 public int getPressure() {
 return pressure;
 }
 public void setPressure(int pressure) {
 this.pressure = pressure;
 }


 }