package com.example.calendar.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.example.calendar.R;
import com.example.calendar.adapter.BaseItemAdapter;
import com.example.calendar.adapter.ItemAdapterState;
import com.example.calendar.listener.NotifyListener;
import com.example.calendar.utils.ViewFactory;
import java.util.Date;

/**
 * Created by Woong on 2017/5/18.
 * @author woong
 */
public class CalendarItemView extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "CalendarItemView";

    private Date mDate;
    private NotifyListener mNotifyListener;
    private BaseItemAdapter mBaseItemAdapter;

    public CalendarItemView(Context context) {
        this(context, null);
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mNotifyListener != null) {
            mNotifyListener.onDateClick(mDate);
        }
    }

    public void setNotifyListener(NotifyListener notifyListener) {
        mNotifyListener = notifyListener;
    }

    /**
     * 设置Adapter
     */
    public void setAdapter(BaseItemAdapter baseItemAdapter) {
        mBaseItemAdapter = baseItemAdapter;
    }

    /**
     * 设置Date
     * @param date
     */
    public void setDate(Date date, @ItemAdapterState int dateState) {
        mDate = date;
        if (mBaseItemAdapter != null) {
            mBaseItemAdapter.bindView(date, dateState);
        }
    }
}

