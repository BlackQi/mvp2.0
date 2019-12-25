package com.qzp.mymvpframe.view.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qzp.mymvpframe.R;
import com.qzp.mymvpframe.base.BaseFragment;
import com.qzp.mymvpframe.base.EventCenter;
import com.qzp.mymvpframe.model.bean.MainBean;
import com.qzp.mymvpframe.model.bean.TestEntity;
import com.qzp.mymvpframe.util.utils.GlideUtils;
import com.qzp.mymvpframe.view.test.ITestView;
import com.qzp.mymvpframe.view.test.TestActivity;
import com.qzp.mymvpframe.view.test.TestPresenter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by qzp on 2018/11/22.
 */

public class MineFragment extends BaseFragment<TestPresenter> implements ITestView, View.OnClickListener {

    private ImageView image;
    private Button takeCount;
    private TextView time;

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test1;
    }

    @Override
    protected void onFirstVisible() {
        mPresenter.getMainData(mContext);
        image.setOnClickListener(this);
        takeCount.setOnClickListener(this);

    }


    @Override
    protected void onVisibleChange(boolean b) {

    }

    @Override
    protected View getLoadingTargetView() {
        return mView.findViewById(R.id.fragment_image);
    }

    @Override
    protected void initView() {
        image = mView.findViewById(R.id.fragment_image);
        takeCount = mView.findViewById(R.id.take_count);
        time = mView.findViewById(R.id.time);
    }


    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    public void onsuccess(MainBean t) {
        GlideUtils.imageLoader(mContext,t.getCarouselList().get(0).getImgUrl(),image);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fragment_image:
                readyGo(TestActivity.class);
                break;
            case R.id.take_count:
                takeTime();
                break;
            default:
        }

    }


    /**
     * 获取计算时间
     */
    public void takeTime(){

        /**
         * 准备数据
         */
        ArrayList<TestEntity> arrayList = new ArrayList<>();
        arrayList.add(new TestEntity("h",10));
        arrayList.add(new TestEntity("i",18));
        arrayList.add(new TestEntity("j",20));
        arrayList.add(new TestEntity("k",3));
        arrayList.add(new TestEntity("l",5));
        arrayList.add(new TestEntity("m",24));
        arrayList.add(new TestEntity("n",45));

        ArrayList<TestEntity> arrayList2 = new ArrayList<>();
        arrayList2.add(new TestEntity("a",1));
        arrayList2.add(new TestEntity("b",34));
        arrayList2.add(new TestEntity("c",54));
        arrayList2.add(new TestEntity("d",56));
        arrayList2.add(new TestEntity("e",6));
        arrayList2.add(new TestEntity("f",45));
        arrayList2.add(new TestEntity("g",67));

        ArrayList<TestEntity> arrayList3 = new ArrayList<>();
        arrayList3.add(new TestEntity("o",89));
        arrayList3.add(new TestEntity("p",87));
        arrayList3.add(new TestEntity("q",56));
        arrayList3.add(new TestEntity("r",98));
        arrayList3.add(new TestEntity("s",56));
        arrayList3.add(new TestEntity("t",34));
        arrayList3.add(new TestEntity("u",68));

        ArrayList<TestEntity> arrayList4 = new ArrayList<>();
        arrayList4.add(new TestEntity("v",34));
        arrayList4.add(new TestEntity("w",35));
        arrayList4.add(new TestEntity("x",56));
        arrayList4.add(new TestEntity("y",34));
        arrayList4.add(new TestEntity("z",23));
        arrayList4.add(new TestEntity("s",57));
        arrayList4.add(new TestEntity("g",68));

        ArrayList<TestEntity> arrayList5 = new ArrayList<>();
        arrayList5.add(new TestEntity("d",34));
        arrayList5.add(new TestEntity("x",35));
        arrayList5.add(new TestEntity("e",56));
        arrayList5.add(new TestEntity("b",34));
        arrayList5.add(new TestEntity("t",23));
        arrayList5.add(new TestEntity("s",57));
        arrayList5.add(new TestEntity("h",68));
        //实验空集合
        ArrayList<TestEntity> arrayList6 = new ArrayList<>();

        ArrayList<ArrayList<TestEntity>> objects = new ArrayList<>();
        objects.add(arrayList);
        objects.add(arrayList2);
        objects.add(arrayList3);
        objects.add(arrayList4);
        objects.add(arrayList5);
        objects.add(arrayList6);

        /**
         * 再随机创建44个集合 每个集合包含50条随机数据
         */
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 44; i++) {
            ArrayList<TestEntity> testArr = new ArrayList<>();
            for (int j = 0; j < 50; j++) {
                testArr.add(new TestEntity(chars.charAt((int)(Math.random() * 26))+"",(int)(1+Math.random()*(10-1+1))));
            }
            objects.add(testArr);
        }

        /**
         * 开始计算
         */

        long start = System.currentTimeMillis();

        //取出所有元素的key添加到一个新的集合
        ArrayList<String> allKey = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            ArrayList<TestEntity> arrayLis = objects.get(i);
            for (int j = 0; j < arrayLis.size(); j++) {
                String name = arrayLis.get(j).getName();
                if (allKey.contains(name)){
                    continue;
                }else {
                    allKey.add(name);
                }
            }
        }


        Log.e("allKey", allKey.toString());
        /**
         * 排序
         */
        Collections.sort(allKey);

        Log.e("allKey", allKey.toString());



        for (int i = 0; i < objects.size(); i++) {
            //创建一个新集合 用于替换当前子集合
            ArrayList<TestEntity> list = new ArrayList<>();
            //当前子集合
            ArrayList<TestEntity> selectList = objects.get(i);

            if (null == selectList || selectList.size() == 0){
                for (int j = 0; j < allKey.size(); j++) {
                    list.add(new TestEntity(allKey.get(j),0));
                }

            }else {

                for (int j = 0; j < allKey.size(); j++) {

                    /**
                     * 依次循环所有key的集合 按照key集合的顺序
                     * 如果当前子集合包含当前key 就添加进去 如果不包含 就添加一个值为0的对象
                     */

                    String s = allKey.get(j);
                    int position = -1;
                    for (int k = 0; k < selectList.size(); k++) {
                        if (s.equals(selectList.get(k).getName())){
                            position = k;
                            break;
                        }
                    }
                    if (position != -1){
                        list.add(new TestEntity(s,selectList.get(position).getNum()));
                    }else {
                        list.add(new TestEntity(s,0));
                    }
                }
            }
            //替换
            objects.get(i).clear();
            objects.get(i).addAll(list);
        }

        long end = System.currentTimeMillis();
        long realyTime = end - start;
        time.setText("开始时间：" +start + "\n" + "结束时间：" + end + "\n" + "总用时：" + realyTime);

        for (int i = 0; i < objects.size(); i++) {
            Log.e("takeTime: ", objects.get(i).toString());
        }

    }
}
