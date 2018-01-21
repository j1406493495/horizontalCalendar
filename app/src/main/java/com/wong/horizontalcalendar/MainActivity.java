package com.wong.horizontalcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.woong.calendar.adapter.DefaultItemAdapter;
import com.woong.calendar.listener.CalendarListener;
import com.woong.calendar.view.CalendarViewPager;
import java.util.Date;

/**
 * @author woong
 */
public class MainActivity extends AppCompatActivity {
    CalendarViewPager mDefaultCalendarViewPager;
    CalendarViewPager mCustomCalendarViewPager;
    CustomItemAdapter mCustomItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mDefaultCalendarViewPager = (CalendarViewPager) findViewById(R.id.default_calendar_viewpager);
        mCustomCalendarViewPager = (CalendarViewPager) findViewById(R.id.custom_calendar_viewpager);

        mDefaultCalendarViewPager.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(MainActivity.this, "default calendar" + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mCustomItemAdapter = new CustomItemAdapter(this);
        mCustomCalendarViewPager.setItemAdapter(mCustomItemAdapter);
        mCustomCalendarViewPager.setTotalWeeks(-20, 20);
        mCustomCalendarViewPager.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(MainActivity.this, "custom calendar" + date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
