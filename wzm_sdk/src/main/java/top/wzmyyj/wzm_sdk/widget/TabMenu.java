package top.wzmyyj.wzm_sdk.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.R;


/**
 * Created by wzm on 2018/04/21. email: 2209011667@qq.com
 */

public class TabMenu extends LinearLayout {

    private int which = 0;
    private int item_count = 2;

    final static private int ITEM_COUNT_MIN = 2;
    final static private int ITEM_COUNT_MAX = 6;

    private int icon_size;
    private int text_size;
    private int item_bg;
    private int text_color1;
    private int text_color2;

    private List<LinearLayout> layouts;
    private List<ImageView> images;
    private List<TextView> texts;

    private String[] str = new String[]{"Item1", "Item2", "Item3", "Item4", "Item5", "Item6"};
    private int[] icon1 = new int[6];
    private int[] icon2 = new int[6];

    private ViewPager mViewPager;

    private OnMenuItemClickListener mMenuItemClickListener;

    public void setOnMenuItemClickListener(
            OnMenuItemClickListener mMenuItemClickListener) {
        this.mMenuItemClickListener = mMenuItemClickListener;
    }


    public interface OnMenuItemClickListener {
        void onClick(View view, int pos);
    }

    public TabMenu(Context context) {
        this(context, null);
    }

    public TabMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOrientation(LinearLayout.HORIZONTAL);

        layouts = new ArrayList<>();
        images = new ArrayList<>();
        texts = new ArrayList<>();

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.TabMenu, defStyle, 0);
        which = a.getInt(R.styleable.TabMenu_which, 0);
        item_count = a.getInt(R.styleable.TabMenu_item_count, 2);
        icon_size = (int) a.getDimension(R.styleable.TabMenu_icon_size, TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
                        getResources().getDisplayMetrics()));
        text_size = a.getDimensionPixelSize(R.styleable.TabMenu_android_textSize, 13);
        item_bg = a.getResourceId(R.styleable.TabMenu_item_bg, 0);
        text_color1 = a.getColor(R.styleable.TabMenu_text_color1, 0x777777);
        text_color2 = a.getColor(R.styleable.TabMenu_text_color2, 0x222222);
        a.recycle();
        initView();
        initData();


    }

    private void initView() {
        for (int i = 0; i < ITEM_COUNT_MAX; i++) {
            final LinearLayout layout = new LinearLayout(this.getContext());
            this.addView(layout, i);
            layout.setVisibility(View.GONE);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(item_bg);
            layout.setClickable(true);
            LayoutParams param1 = new LayoutParams(
                    0, LayoutParams.MATCH_PARENT);
            param1.weight = 1;
            layout.setLayoutParams(param1);
            //icon
            ImageView img = new ImageView(this.getContext());
            layout.addView(img);
            LayoutParams param2 = new LayoutParams(
                    icon_size, icon_size);
            param2.topMargin = icon_size / 3;
            param2.gravity = Gravity.CENTER_HORIZONTAL;
            img.setLayoutParams(param2);
            //text
            TextView tv = new TextView(this.getContext());
            layout.addView(tv);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
            LayoutParams param3 = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            param3.gravity = Gravity.CENTER_HORIZONTAL;
            tv.setLayoutParams(param3);
            tv.setGravity(Gravity.CENTER);

            final int w = i;
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMenuItemClickListener != null) {
                        mMenuItemClickListener.onClick(layout, w);
                        change(w);
                    }
                }
            });

            layouts.add(layout);
            images.add(img);
            texts.add(tv);
        }
    }

    private TabMenu limitCount(int item_count) {
        this.item_count = (item_count > ITEM_COUNT_MAX) ? ITEM_COUNT_MAX :
                (item_count < ITEM_COUNT_MIN) ? ITEM_COUNT_MIN : item_count;
        return this;
    }

    private TabMenu limitWhich(int which) {
        this.which = (which > this.item_count) ? which % this.item_count : which;
        return this;
    }

    public TabMenu setItemText(String[] str) {
        if (str != null) {
            for (int i = 0; i < str.length; i++) {
                if (i > this.str.length - 1) {
                    break;
                }
                this.str[i] = str[i];
            }
        }
        for (int i = 0; i < ITEM_COUNT_MAX; i++) {
            texts.get(i).setText(this.str[i]);
        }
        return this;
    }

    public TabMenu setItemTextColor(int text_color1, int text_color2) {
        this.text_color1 = text_color1;
        this.text_color2 = text_color2;
        return this.setItemTextColor();
    }

    private TabMenu setItemTextColor() {
        for (int i = 0; i < ITEM_COUNT_MAX; i++) {
            if (i == this.which) {
                texts.get(i).setTextColor(this.text_color2);
            } else {
                texts.get(i).setTextColor(this.text_color1);
            }
        }
        return this;
    }

    public TabMenu setItemIcon(int[] icon1, int[] icon2) {
        if (icon1 != null) {
            for (int i = 0; i < icon1.length; i++) {
                if (i > this.icon1.length - 1) {
                    break;
                }
                this.icon1[i] = icon1[i];
            }
        }
        if (icon2 != null) {
            for (int i = 0; i < icon2.length; i++) {
                if (i > this.icon2.length - 1) {
                    break;
                }
                this.icon2[i] = icon2[i];
            }
        }
        return this.setItemIcon();
    }

    private TabMenu setItemIcon() {
        for (int i = 0; i < ITEM_COUNT_MAX; i++) {
            if (i == this.which) {
                images.get(i).setBackgroundResource(this.icon2[i]);
            } else {
                images.get(i).setBackgroundResource(this.icon1[i]);
            }
        }

        return this;
    }


    private TabMenu setItemVisibility() {
        for (int i = 0; i < ITEM_COUNT_MAX; i++) {
            if (i < item_count) {
                layouts.get(i).setVisibility(View.VISIBLE);
            } else {
                layouts.get(i).setVisibility(View.GONE);
            }
        }
        return this;
    }

    private void initData() {
        initItem(this.item_count, this.which, this.str, this.icon1, this.icon2);
    }

    public void initItem(int item_count, int which, String[] str, int[] icon1, int[] icon2) {
        this.limitCount(item_count)
                .limitWhich(which)
                .setItemVisibility()
                .setItemText(str)
                .setItemTextColor()
                .setItemIcon(icon1, icon2);
    }


    public void change(int which) {
        if (which != this.which) {
            this.limitWhich(which).setItemTextColor().setItemIcon();
        }
    }

    public TabMenu setupWithViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            return this;
        }
        mViewPager = viewPager;
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter != null) {
            this.limitCount(adapter.getCount())
                    .limitWhich(mViewPager.getCurrentItem())
                    .setItemVisibility()
                    .setItemTextColor()
                    .setItemIcon();
        }

        this.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                mViewPager.setCurrentItem(pos, false);
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabMenu.this.change(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return this;

    }


}