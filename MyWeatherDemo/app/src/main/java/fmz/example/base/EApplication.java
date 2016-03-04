package fmz.example.base;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by zzk on 15/12/7.
 */
public class EApplication extends Application{
    public static Context mAppContext = null;
    private static EApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this) ;
        LeakCanary.install(this);
        mAppContext = getApplicationContext();
    }

    public static EApplication getInstance(){
        if(instance == null){
            synchronized (EApplication.class){
                if(instance == null){
                    instance = new EApplication();
                }
            }
        }
        return instance;
    }
}
