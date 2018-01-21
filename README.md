# horizontalCalendar

**水平日历滚动控件，实现日历按周滚动，可自定义item布局。**



## 效果图：

![Screenrecorder-2018-01-21-15-44-46-314](https://github.com/j1406493495/horizontalCalendar/blob/master/gif/horizontalCalendar.gif?raw=true)

## gradle导入：

> ```
> dependencies {
>     compile "com.woong:HorizontalCalendar:1.0.0"
> }
> ```



## 接入使用：

xml布局中添加使用：

```
<com.woong.calendar.view.CalendarViewPager
    android:id="@+id/default_calendar_viewpager"
    android:layout_width="match_parent"
    android:layout_height="120dp"/>
```

item点击事件回调：

```
mDefaultCalendarViewPager.setCalendarListener(new CalendarListener() {
    @Override
    public void onDateSelected(Date date) {
        Toast.makeText(MainActivity.this, "default calendar" + date.toString(), Toast.LENGTH_SHORT).show();
    }
});
```

自定义item布局：

自定义ItemAdapter继承BaseItemAdapter，实现getView和bindView，具体实现如下所示：

```
public class CustomItemAdapter extends BaseItemAdapter {
    private Context mContext;

    public CustomItemAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void bindView(Date date, View view, int dateState) {
        //自定义holder绑定数据
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        
        if (dateState == DateState.STATE_SELECT) {
            //item点击选中
        } else if (dateState == DateState.STATE_TODAY) {
            //当前日期
        } else if (dateState == DateState.STATE_NORMAL) {
            //其他普通日期
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflate自定义item布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_custom, parent, false);
        return view;
    }

	/**
	* 自定义ViewHolder类
	**/
    public class CustomViewHolder {
        CustomViewHolder(View itemView) {
        }
    }
}
```

```
mCustomItemAdapter = new CustomItemAdapter(this);
mCustomCalendarViewPager.setItemAdapter(mCustomItemAdapter);
```

配置项：

```
//设置日历的区间，目前只支持以当前日期为原点，前后周数设置
mCustomCalendarViewPager.setTotalWeeks(-20, 20);
```



---

本文由 [Woong](http://woong.com.cn/) 创作，采用 [知识共享署名4.0](https://creativecommons.org/licenses/by/4.0/) 国际许可协议进行许可
本站文章除注明转载/出处外，均为本站原创或翻译，转载前请务必署名