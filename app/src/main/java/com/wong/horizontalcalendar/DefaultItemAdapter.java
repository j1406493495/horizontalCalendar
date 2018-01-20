package com.wong.horizontalcalendar;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woong.calendar.R;
import com.woong.calendar.adapter.BaseItemAdapter;
import com.woong.calendar.adapter.ItemAdapterState;
import com.woong.calendar.utils.ViewFactory;

import java.util.Date;

/**
 * Created by wong on 2018/1/6.
 */

public class DefaultItemAdapter extends BaseItemAdapter {
    private String formatWeek = "EEE";
    private String formatDay = "dd";
    private String formatMonth = "MMM";

    private Context mContext;

    public DefaultItemAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent, false);
        return view;
    }

    @Override
    public void bindView(Date date, View view, int dateState) {
        DefaultDateViewHolder viewHolder = new DefaultDateViewHolder(view);
        viewHolder.tvWeek.setText(DateFormat.format(formatWeek, date).toString());
        viewHolder.tvDay.setText(DateFormat.format(formatDay, date).toString());
        viewHolder.tvMonth.setText(DateFormat.format(formatMonth, date).toString());

        if (dateState == ItemAdapterState.STATE_NORMAL) {
            viewHolder.llCalendarItem.setBackgroundResource(R.color.transparent);
            viewHolder.setTextColor(mContext.getResources().getColor(R.color.b3));
        } else if (dateState == ItemAdapterState.STATE_SELECT) {
            viewHolder.llCalendarItem.setBackgroundResource(R.drawable.ic_calendar_select);
            viewHolder.setTextColor(mContext.getResources().getColor(R.color.b7));
        } else if (dateState == ItemAdapterState.STATE_TODAY) {
            viewHolder.llCalendarItem.setBackgroundResource(R.drawable.ic_calendar_today);
            viewHolder.setTextColor(mContext.getResources().getColor(R.color.b3));
        }
    }

    public class DefaultDateViewHolder {
        private ViewFactory mViewFactory;

        TextView tvWeek;
        TextView tvDay;
        TextView tvMonth;
        LinearLayout llCalendarItem;

        DefaultDateViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }

            mViewFactory = ViewFactory.getInstance(mContext);
            llCalendarItem = mViewFactory.getView(itemView, R.id.ll_calendar_item);
            tvWeek = mViewFactory.getView(itemView, R.id.tv_week);
            tvDay = mViewFactory.getView(itemView, R.id.tv_day);
            tvMonth = mViewFactory.getView(itemView, R.id.tv_month);
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
}
