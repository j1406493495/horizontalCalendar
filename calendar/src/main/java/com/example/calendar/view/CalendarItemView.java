package com.example.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.calendar.R;
import com.example.calendar.listener.NotifyListener;
import com.example.calendar.utils.ViewFactory;
import java.util.Date;

/**
 * Created by Woong on 2017/5/18.
 * @author woong
 */
public class CalendarItemView extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "CalendarItemView";

    private Context mContext;
    private ViewFactory mViewFactory;
    private Date mDate;
    private NotifyListener mNotifyListener;

    private TextView tvWeek;
    private TextView tvDay;
    private TextView tvMonth;
    private LinearLayout mllCalendarItem;

    public CalendarItemView(Context context) {
        this(context, null);
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mViewFactory = ViewFactory.getInstance(mContext);

        View view = inflate(mContext, R.layout.item_calendar, this);
        mllCalendarItem = mViewFactory.getView(view, R.id.ll_calendar_item);
        tvWeek = mViewFactory.getView(view, R.id.tv_week);
        tvDay = mViewFactory.getView(view, R.id.tv_day);
        tvMonth = mViewFactory.getView(view, R.id.tv_month);

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
     * 设置当前星期
     * @param week
     */
    public void setWeek(String week) {
        tvWeek.setText(week);
    }

    /**
     * 设置当前日期
     * @param day
     */
    public void setDay(String day) {
        tvDay.setText(day);
    }

    /**
     * 设置当前月份
     * @param month
     */
    public void setMonth(String month) {
        tvMonth.setText(month);
    }

    /**
     * 设置Date
     * @param date
     */
    public void setDate(Date date) {
        mDate = date;
    }

    /**
     * 设置字体颜色
     * @param color
     */
    public void setTextColor(int color) {
        tvWeek.setTextColor(color);
        tvDay.setTextColor(color);
        tvMonth.setTextColor(color);
    }
}

