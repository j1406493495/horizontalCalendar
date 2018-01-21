package com.wong.horizontalcalendar;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.woong.calendar.adapter.BaseItemAdapter;
import com.woong.calendar.adapter.DateState;
import com.woong.calendar.utils.ViewFactory;

import java.util.Date;

/**
 * Created by wong on 2018/1/21.
 */

public class CustomItemAdapter extends BaseItemAdapter {
    private Context mContext;
    private String formatDay = "dd";
    private String formatMonth = "MMM";

    public CustomItemAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void bindView(Date date, View view, int dateState) {
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        customViewHolder.tvMonth.setText(DateFormat.format(formatMonth, date).toString());
        customViewHolder.tvDay.setText(DateFormat.format(formatDay, date).toString());

        if (dateState == DateState.STATE_SELECT) {
            customViewHolder.llCustomItem.setBackgroundColor(Color.BLUE);
        } else if (dateState == DateState.STATE_TODAY) {
            customViewHolder.llCustomItem.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_custom, parent, false);
        return view;
    }

    public class CustomViewHolder {
        private ViewFactory mViewFactory;

        LinearLayout llCustomItem;
        TextView tvMonth;
        TextView tvDay;

        CustomViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }

            mViewFactory = ViewFactory.getInstance(mContext);
            llCustomItem = mViewFactory.getView(itemView, R.id.ll_custom_item);
            tvMonth = mViewFactory.getView(itemView, R.id.tv_month);
            tvDay = mViewFactory.getView(itemView, R.id.tv_day);
        }
    }
}
