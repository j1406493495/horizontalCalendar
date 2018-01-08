package com.example.wong.horizontalcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.calendar.adapter.BaseItemAdapter;
import com.example.calendar.listener.CalendarListener;
import com.example.calendar.view.CalendarViewPager;
import java.util.Date;

/**
 * @author woong
 */
public class MainActivity extends AppCompatActivity {
    CalendarViewPager mCalendarViewPager;
    DefaultItemAdapter mDefaultItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        mDefaultItemAdapter = new DefaultItemAdapter(this);
        mDefaultItemAdapter = new DefaultItemAdapter(this);

        mCalendarViewPager = (CalendarViewPager) findViewById(R.id.calendar_view_pager);
        mCalendarViewPager.setItemAdapter(mDefaultItemAdapter);
        mCalendarViewPager.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(MainActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
