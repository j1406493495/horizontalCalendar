package com.example.calendar.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by wong on 2018/1/2.
 * @author woong
 */
public class ViewFactory extends View{
    private ViewFactory(Context context) {
        super(context);
    }

    private static volatile ViewFactory instance = null;
    public static ViewFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (ViewFactory.class) {
                if (instance == null){
                    instance = new ViewFactory(context);
                }
            }
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public final <E extends View> E getView (View view, int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException e) {
            throw e;
        }
    }
}
