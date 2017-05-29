package com.wtfcompany.nasafeed;

import android.content.Context;

/**
 * Created by Ijin on 28.05.2017.
 */

public  class CurrentContext {

    private static Context context;
    private static CurrentContext instance;

    private CurrentContext(){}

    public static Context getContext(){
        return context;
    }
    public static void setContext(Context _context){
        context = _context;
    }

    public static synchronized CurrentContext getInstance(){
        if(instance == null){
            instance = new CurrentContext();
        }
        return instance;
    }
}
