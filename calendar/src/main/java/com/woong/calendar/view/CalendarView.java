package com.woong.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.TimeUtils;
import com.woong.calendar.adapter.BaseItemAdapter;
import com.woong.calendar.adapter.ItemAdapterState;
import com.woong.calendar.listener.NotifyListener;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Woong on 2017/5/18.
 * @author woong
 */
public class CalendarView extends LinearLayout{
    private static final String TAG = "CalendarView";

    private BaseItemAdapter mBaseAdapter;
    private NotifyListener mNotifyListener;
    private ArrayList<Date> mDates = new ArrayList<>();

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            LayoutParams childLayoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            int childWidth = measureWidth / count - childLayoutParams.leftMargin - childLayoutParams.rightMargin;
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
            getChildAt(i).measure(childWidthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean isDateEqual(Date firstDate, Date secondDate) {
        String firstString = TimeUtils.date2String(firstDate);
        String secondString = TimeUtils.date2String(secondDate);
        return firstString.equals(secondString);
    }

    public void setNotifyListener(NotifyListener notifyListener) {
        mNotifyListener = notifyListener;
    }

    public void setAdapter(BaseItemAdapter baseAdapter) {
        mBaseAdapter = baseAdapter;
    }

    private void buildViews() {
        removeAllViews();
        for (int i = 0; i < 7; i++) {
            final int pos = i;
            View view = mBaseAdapter.getView(i, null, this);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNotifyListener != null && mDates.size() > pos) {
                        mNotifyListener.onDateClick(mDates.get(pos));
                    }
                }
            });
            addView(view, i);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (mBaseAdapter != null) {
            buildViews();
        }
        super.onAttachedToWindow();
    }

    /**
     * 设置页面日期信息
     * @param dateList 日期列表
     * @param position 日期位于第几周
     */
    public void setDates(ArrayList<Date> dateList, int position, Date selectDate) {
        mDates.clear();
        int realPosition = position * 7;
        for (int i = 0; i < 7; i++) {
            Date date = dateList.get(realPosition + i);
            mDates.add(date);

            if (mBaseAdapter != null) {
                if (isDateEqual(date, selectDate)) {
                    mBaseAdapter.bindView(date, getChildAt(i), ItemAdapterState.STATE_SELECT);
                } else {
                    if (TimeUtils.isToday(date)) {
                        mBaseAdapter.bindView(date, getChildAt(i), ItemAdapterState.STATE_TODAY);
                    } else {
                        mBaseAdapter.bindView(date, getChildAt(i), ItemAdapterState.STATE_NORMAL);
                    }
                }
            }
        }
    }
}
