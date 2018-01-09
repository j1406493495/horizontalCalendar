package com.woong.calendar.adapter;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wong on 2018/1/6.
 * @author woong
 */
@IntDef({ItemAdapterState.STATE_NORMAL, ItemAdapterState.STATE_SELECT, ItemAdapterState.STATE_TODAY})
@Retention(RetentionPolicy.SOURCE)
public @interface ItemAdapterState {
    int STATE_NORMAL = 0;
    int STATE_SELECT = 1;
    int STATE_TODAY = 2;
}
