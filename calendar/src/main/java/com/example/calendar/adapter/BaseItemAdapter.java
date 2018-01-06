package com.example.calendar.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.Date;

/**
 * Created by wong on 2018/1/6.
 */

public abstract class BaseItemAdapter {
   Context mContext;
    public BaseItemAdapter(Context context) {
        mContext = context;
    }

    public abstract View getView();

    public abstract void bindView(Date date, @ItemAdapterState int dateState);
}
