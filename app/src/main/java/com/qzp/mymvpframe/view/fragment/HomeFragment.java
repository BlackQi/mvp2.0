package com.qzp.mymvpframe.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseFragment;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.HomeFragmentBannerBean;
import com.qzp.mymvpframe.presenter.HomefragmentPresenter;
import com.qzp.mymvpframe.util.network.NetUtils;
import com.qzp.mymvpframe.util.utils.ToastUtils;
import com.qzp.mymvpframe.view.iview.IHomefragmentView;

import java.util.ArrayList;

/**
 * Created by qzp on 2018/11/22.
 */

public class HomeFragment extends BaseFragment<HomefragmentPresenter> implements IHomefragmentView, OnChartValueSelectedListener {


    private BarChart barChart;
    private BarData barData;

    @Override
    protected void createPresenter() {
        mPresenter = new HomefragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onFirstVisible() {
        if (NetUtils.isNetworkConnected(mContext)){
            mPresenter.getBanner(mContext);
        }
        initBarChatData();
        initBarChat();
    }


    @Override
    protected void onVisibleChange(boolean b) {

    }



    @Override
    protected View getLoadingTargetView() {
        return mView.findViewById(R.id.homefragment_parent);
    }

    @Override
    protected void initView() {
        barChart = mView.findViewById(R.id.bChart);
    }


    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    public void loadBannerSuccess(HomeFragmentBannerBean homeFragmentBannerBean) {
        ToastUtils.showShort(mContext,"请求成功"+homeFragmentBannerBean.getCarouselList().get(0).getImgUrl());
    }

    @Override
    public void loadBannerError(String message) {

    }

    private void initBarChat(){

        //设置属性和数据
        barChart.getDescription().setEnabled(false);
        //设置是否支持手势操作
        barChart.setOnChartValueSelectedListener(this);
        //设置是否绘制网格布局
        barChart.setDrawGridBackground(false);
        //是否绘制柱形图阴影
        barChart.setDrawBarShadow(false);
        //设置字体类型
//        Typeface tf = Typeface.createFromAsset(this.getAssets(),"OpenSans-Light.ttf");
        //设置数据
        barChart.setData(barData);
        //设置图例
        Legend l = barChart.getLegend();
        //设置图例的字体
//        l.setTypeface(tf);
        //得到表格左边的轴线
        YAxis leftAxis = barChart.getAxisLeft();
        //设置轴线上边字体的类型
//      leftAxis.setTypeface(tf);
        //设置轴线的最小粗度
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        //禁用表格右轴线
        barChart.getAxisRight().setEnabled(false);
        //禁用表格的顶部轴线
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);
    }

    private void initBarChatData() {
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for(int i=0;i<5;i++){
            BarEntry barEntry = new BarEntry(i + 1, (float) (Math.random() * 100));
            entries.add(barEntry);
        }
        BarDataSet ds = new BarDataSet(entries, "亮亮");
        ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
        ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
        sets.add(ds);
        barData = new BarData(sets);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        ToastUtils.showShort(mContext,e.toString() + h.toString());
    }

    @Override
    public void onNothingSelected() {

    }
}
