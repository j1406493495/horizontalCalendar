package com.woong.calendar.adapter;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import java.util.Date;

/**
 * Created by wong on 2018/1/6.
 * @author woong
 */
public abstract class BaseItemAdapter extends BaseAdapter{
    Context mContext;
    public BaseItemAdapter(Context context) {
        mContext = context;
    }

    /**
     * bind date data
     * @param date
     * @param dateState
     */
    public abstract void bindView(Date date, View view, @ItemAdapterState int dateState);

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
