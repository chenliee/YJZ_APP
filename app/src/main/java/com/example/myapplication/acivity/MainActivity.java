package com.example.myapplication.acivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.myapplication.Adapter.MyFragmentPagerAdapter;
import com.example.myapplication.BaseActivity;
import com.example.myapplication.R;
import com.example.myapplication.fragment.MainFragment;
import com.example.myapplication.router.RouterPath;

import java.util.ArrayList;

@Route(path = RouterPath.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity implements View.OnClickListener{
    ViewPager2 viewPager;
    private LinearLayout lHome,lExplore,lAddOil,lMall,lMine;
    private ImageView iHome,iExplore,iAddOil,iMall,iMine,iCurrent;
    private TextView tHome,tExplore,tAddOil,tMall,tMine,tCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initTabView();
    }

    private void initTabView() {
        lHome = findViewById(R.id.ll_tab_one);
        lHome.setOnClickListener(this);
        lExplore = findViewById(R.id.ll_tab_two);
        lExplore.setOnClickListener(this);
        lAddOil = findViewById(R.id.ll_tab_three);
        lAddOil.setOnClickListener(this);
        lMall = findViewById(R.id.ll_tab_four);
        lMall.setOnClickListener(this);
        lMine = findViewById(R.id.ll_tab_five);
        lMine.setOnClickListener(this);
        iHome = findViewById(R.id.iv_tab_one);
        iExplore = findViewById(R.id.iv_tab_two);
        iAddOil = findViewById(R.id.iv_tab_three);
        iMall = findViewById(R.id.iv_tab_four);
        iMine = findViewById(R.id.iv_tab_five);
        iHome.setSelected(true);
        iCurrent = iHome;
        tHome = findViewById(R.id.tv_tab_one);
        tExplore = findViewById(R.id.tv_tab_two);
        tAddOil = findViewById(R.id.tv_tab_three);
        tMall = findViewById(R.id.tv_tab_four);
        tMine = findViewById(R.id.tv_tab_five);
        tHome.setTextColor(Color.parseColor("#FFFF8008"));
        tCurrent = tHome;
    }

    private void initPager() {
        viewPager = findViewById(R.id.id_viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(MainFragment.newInstance("首页"));
        fragments.add(MainFragment.newInstance("导航"));
        fragments.add(MainFragment.newInstance("一键加油"));
        fragments.add(MainFragment.newInstance("商城"));
        fragments.add(MainFragment.newInstance("我的"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        iCurrent.setSelected(false);
        tCurrent.setTextColor(Color.parseColor("#A4A4A4"));
        switch (position){
            case R.id.ll_tab_one:
                viewPager.setCurrentItem(0);
            case 0:
                iHome.setSelected(true);
                tHome.setTextColor(Color.parseColor("#FFFF8008"));
                iCurrent = iHome;
                tCurrent = tHome;
                break;
            case R.id.ll_tab_two:
                viewPager.setCurrentItem(1);
            case 1:
                iExplore.setSelected(true);
                tExplore.setTextColor(Color.parseColor("#FFFF8008"));
                iCurrent = iExplore;
                tCurrent = tExplore;
                break;
            case R.id.ll_tab_three:
                viewPager.setCurrentItem(2);
            case 2:
                iCurrent = iAddOil;
                tAddOil.setTextColor(Color.parseColor("#FFFF8008"));
                tCurrent = tAddOil;
                break;
            case R.id.ll_tab_four:
                viewPager.setCurrentItem(3);
            case 3:
                iMall.setSelected(true);
                tMall.setTextColor(Color.parseColor("#FFFF8008"));
                iCurrent = iMall;
                tCurrent = tMall;
                break;
            case R.id.ll_tab_five:
                viewPager.setCurrentItem(4);
            case 4:
                iMine.setSelected(true);
                tMine.setTextColor(Color.parseColor("#FFFF8008"));
                iCurrent = iMine;
                tCurrent = tMine;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }

}