package com.example.wong.horizontalcalendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.calendar.adapter.BaseItemAdapter;
import com.example.calendar.adapter.ItemAdapterState;
import com.example.calendar.utils.ViewFactory;

import java.util.Date;

/**
 * Created by wong on 2018/1/6.
 */

public class DefaultItemAdapter extends BaseItemAdapter {
    private String formatWeek = "EEE";
    private String formatDay = "dd";
    private String formatMonth = "MMM";

    private TextView tvWeek;
    private TextView tvDay;
    private TextView tvMonth;
    private LinearLayout llCalendarItem;

    private ViewFactory mViewFactory;
    private Context mContext;

    public DefaultItemAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null);
        mViewFactory = ViewFactory.getInstance(mContext);
        llCalendarItem = mViewFactory.getView(view, R.id.ll_calendar_item);
        tvWeek = mViewFactory.getView(view, R.id.tv_week);
        tvDay = mViewFactory.getView(view, R.id.tv_day);
        tvMonth = mViewFactory.getView(view, R.id.tv_month);
        return view;
    }

    @Override
    public void bindView(Date date, int dateState) {
        setWeek(DateFormat.format(formatWeek, date).toString());
        setDay(DateFormat.format(formatDay, date).toString());
        setMonth(DateFormat.format(formatMonth, date).toString());

        if (dateState == ItemAdapterState.STATE_NORMAL) {
            llCalendarItem.findViewById(R.id.ll_calendar_item).setBackgroundResource(R.color.transparent);
            setTextColor(mContext.getResources().getColor(R.color.b3));
        } else if (dateState == ItemAdapterState.STATE_SELECT) {
            llCalendarItem.setBackgroundResource(R.drawable.ic_calendar_select);
            setTextColor(mContext.getResources().getColor(R.color.b7));
        } else if (dateState == ItemAdapterState.STATE_TODAY) {
            llCalendarItem.setBackgroundResource(R.drawable.ic_calendar_today);
            setTextColor(mContext.getResources().getColor(R.color.b3));
        }
    }

    /**
     * 设置当前星期
     * @param week
     */
    private void setWeek(String week) {
        tvWeek.setText(week);
    }

    /**
     * 设置当前日期
     * @param day
     */
    private void setDay(String day) {
        tvDay.setText(day);
    }

    /**
     * 设置当前月份
     * @param month
     */
    private void setMonth(String month) {
        tvMonth.setText(month);
    }

    /**
     * 设置字体颜色
     * @param color
     */
    private void setTextColor(int color) {
        tvWeek.setTextColor(color);
        tvDay.setTextColor(color);
        tvMonth.setTextColor(color);
    }
}
