package com.woong.calendar.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.TimeUtils;
import com.woong.calendar.adapter.BaseItemAdapter;
import com.woong.calendar.listener.CalendarListener;
import com.woong.calendar.listener.NotifyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Woong on 2017/5/18.
 * @author woong
 */
public class CalendarViewPager extends ViewPager{
    private static final String TAG = "CalendarViewPager";
    
    private Context mContext;
    private CalendarListener mCalendarListener;
    private MyPageAdapter mPageAdapter;
    private Date mSelectedDate;
    private ArrayList<CalendarView> mCalendarViewList = new ArrayList<>();

    private int mStartWeek = -10;
    private int mEndWeek = 10;
    private int mTotalCount;

    private ArrayList<Date> mDateList = new ArrayList<>();

    public CalendarViewPager(Context context) {
        this(context, null);
    }

    public CalendarViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initData();
        setCurrentItem(0 - mStartWeek);
        mPageAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mCalendarViewList != null) {
            mCalendarViewList.clear();
        }
    }

    public void onDateSelectedChanged(Date date) {
        mSelectedDate = date;
        mPageAdapter.notifyDataSetChanged();

        if (mCalendarListener != null) {
            mCalendarListener.onDateSelected(date);
        }
    }

    private void initView() {
        for (int i = 0; i < 3; i++) {
            CalendarView calendarView = new CalendarView(mContext);
            calendarView.setNotifyListener(new NotifyListener() {
                @Override
                public void onDateClick(Date date) {
                    if (date != null) {
                        onDateSelectedChanged(date);
                    } else {
                        mPageAdapter.notifyDataSetChanged();
                    }
                }
            });
            mCalendarViewList.add(calendarView);
        }

        mPageAdapter = new MyPageAdapter();
        setAdapter(mPageAdapter);
    }

    private void initData() {
        mTotalCount = mEndWeek - mStartWeek + 1;
        mSelectedDate = TimeUtils.getNowDate();
        initAllDate();
    }

    private void initAllDate() {
        Date today = new Date();
        GregorianCalendar calendar = new GregorianCalendar();

        int startDayIndex;
        int endDayIndex;
        int todayWeekIndex = TimeUtils.getWeekIndex(today);
        if (todayWeekIndex != 1) {
            startDayIndex = 2 - todayWeekIndex;
            endDayIndex = 8 - todayWeekIndex;
        } else {
            startDayIndex = -6;
            endDayIndex = 0;
        }

        calendar.setTime(today);
        calendar.add(Calendar.DATE, startDayIndex + mStartWeek * 7);
        Date dateStartBefore = calendar.getTime();

        calendar.setTime(today);
        calendar.add(Calendar.DATE, endDayIndex + mEndWeek * 7);
        Date dateEndAfter = calendar.getTime();

        Date date = dateStartBefore;
        while (!date.after(dateEndAfter)) {
            mDateList.add(date);
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();
        }
    }

    /**
     * 设置历史最大周数
     * @param startWeek
     */
    public void setStartWeek(int startWeek) {
        /** start before @startMonth month from now */
        if (mStartWeek < 0) {
            mStartWeek = startWeek;
        }
    }

    /**
     * 设置未来最大周数
     * @param endWeek
     */
    public void setEndWeek(int endWeek) {
        /** end after @endMonth month from now */
        if (mEndWeek > 0) {
            mEndWeek = endWeek;
        }
    }

    /**
     * 设置item点击监听
     * @param calendarListener
     */
    public void setCalendarListener(CalendarListener calendarListener) {
        mCalendarListener = calendarListener;
    }

    /**
     * 自定义日期item布局
     * @param baseAdapter
     */
    public void setItemAdapter(BaseItemAdapter baseAdapter) {
        for (CalendarView calendarView : mCalendarViewList) {
            calendarView.setAdapter(baseAdapter);
        }
        mPageAdapter.notifyDataSetChanged();
    }

    public class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mTotalCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View calendarView = mCalendarViewList.get(position % 3);
            if (calendarView.getParent() != null) {
                container.removeView(calendarView);
            }
            container.addView(calendarView);

            mCalendarViewList.get(position % 3).setDates(mDateList, position, mSelectedDate);

            return calendarView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
