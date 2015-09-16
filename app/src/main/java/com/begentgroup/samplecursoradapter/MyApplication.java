package com.begentgroup.samplecursoradapter;

import android.app.Application;
import android.content.Context;

/**
 * Created by dongja94 on 2015-09-16.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
