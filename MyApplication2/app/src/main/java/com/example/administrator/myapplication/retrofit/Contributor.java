package com.example.administrator.myapplication.retrofit;

/**
 * Created by Administrator on 2016/2/25.
 */
public  class Contributor {
    public final String login;
    public final int contributions;
    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}