package com.abc.agency.baseui.ui.view.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abc.agency.baseui.R;
import com.hwangjr.rxbus.RxBus;

import java.util.Date;

/**
 * Created by Woong on 2017/5/18.
 */

public class CalendarItemView extends LinearLayout implements View.OnClickListener{
    private static final String TAG = "CalendarItemView";

    public static final String DATE_SELECTED = "date_selected";
    
    private Context mContext;
    private Date mDate;
    private TextView mTvDayName;
    private TextView mTvDayNumber;
    private TextView mTvMonthName;
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
        inflate(mContext, R.layout.item_calendar, this);
        mllCalendarItem = (LinearLayout) findViewById(R.id.calendar_item);
        mTvDayName = (TextView) findViewById(R.id.dayName);
        mTvDayNumber = (TextView) findViewById(R.id.dayNumber);
        mTvMonthName = (TextView) findViewById(R.id.monthName);

        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RxBus.get().post(DATE_SELECTED, mDate);
    }

    /**
     * 设置当前星期
     * @param dayName
     */
    public void setDayName(String dayName) {
        mTvDayName.setText(dayName);
    }

    /**
     * 设置当前日期
     * @param dayNumber
     */
    public void setDayNumber(String dayNumber) {
        mTvDayNumber.setText(dayNumber);
    }

    /**
     * 设置当前月份
     * @param monthName
     */
    public void setMonthName(String monthName) {
        mTvMonthName.setText(monthName);
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
        mTvDayName.setTextColor(color);
        mTvDayNumber.setTextColor(color);
        mTvMonthName.setTextColor(color);
    }
}

