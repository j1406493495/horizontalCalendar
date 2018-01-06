package com.example.wong.horizontalcalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.calendar.listener.CalendarListener;
import com.example.calendar.utils.ViewFactory;
import com.example.calendar.view.CalendarViewPager;

import java.util.Date;

/**
 * @author woong
 */
public class MainActivity extends AppCompatActivity {
    CalendarViewPager mCalendarViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCalendarViewPager = (CalendarViewPager) findViewById(R.id.calendar_view_pager);
        mCalendarViewPager.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                Toast.makeText(MainActivity.this, date.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
