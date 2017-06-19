package com.abc.agency.baseui.ui.view.calendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.abc.agency.baseui.R;
import com.blankj.utilcode.util.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Woong on 2017/5/18.
 */

public class CalendarView extends LinearLayout{
    private static final String TAG = "CalendarView";

    private Context mContext;
    private ArrayList<CalendarItemView> mItemViewList = new ArrayList<>();

    private String formatDayName = "EEE";
    private String formatDayNumber = "dd";
    private String formatMonth = "MMM";

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.view_calendar, this);

        mItemViewList.add((CalendarItemView)findViewById(R.id.mon));
        mItemViewList.add((CalendarItemView)findViewById(R.id.tue));
        mItemViewList.add((CalendarItemView)findViewById(R.id.wed));
        mItemViewList.add((CalendarItemView)findViewById(R.id.thu));
        mItemViewList.add((CalendarItemView)findViewById(R.id.fri));
        mItemViewList.add((CalendarItemView)findViewById(R.id.sat));
        mItemViewList.add((CalendarItemView)findViewById(R.id.sun));
    }

    private boolean isDateEqual(Date firstDate, Date secondDate) {
        String firstString = TimeUtils.date2String(firstDate);
        String secondString = TimeUtils.date2String(secondDate);
        return firstString.equals(secondString);
    }

    /**
     * 设置页面日期信息
     * @param dateList 日期列表
     * @param position 日期位于第几周
     */
    public void setDates(ArrayList<Date> dateList, int position, Date selectDate) {
        int realPosition = position * 7;
        for (int i = 0; i < 7; i++) {
            Date date = dateList.get(realPosition + i);
            mItemViewList.get(i).setDayName(DateFormat.format(formatDayName, date).toString());
            mItemViewList.get(i).setDayNumber(DateFormat.format(formatDayNumber, date).toString());
            mItemViewList.get(i).setMonthName(DateFormat.format(formatMonth, date).toString());
            mItemViewList.get(i).setDate(date);

            if (isDateEqual(date, selectDate)) {
                mItemViewList.get(i).findViewById(R.id.calendar_item).setBackgroundResource(R.drawable.ic_calendar_select);
                mItemViewList.get(i).setTextColor(getResources().getColor(R.color.b9));
            } else {
                if (TimeUtils.isToday(date)) {
                    mItemViewList.get(i).findViewById(R.id.calendar_item).setBackgroundResource(R.drawable.ic_calendar_today);
                } else {
                    mItemViewList.get(i).findViewById(R.id.calendar_item).setBackgroundResource(R.drawable.bg_transparent);
                }
                mItemViewList.get(i).setTextColor(getResources().getColor(R.color.b3));
            }
        }
    }

}
