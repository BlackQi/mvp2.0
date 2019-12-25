package com.qzp.mymvpframe.util.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by qzp on 2017/12/13.   状态栏工具类
 */

public class WindowStateBarUtils {


    /**
     * @param window window对象
     * @param b      是否改变字体颜色 true改变 false不改变
     *                一般情况下状态栏字体的颜色是白色的，一般写false就行
     *                但是当我们的状态栏也是白色的时候，字体也是白色显然是不合适，所以需要把字体改变成黑色的，这个时候写true
     */
    public static void setWindowStatus(Window window, Boolean b) {

        if (window != null) {
            if (Build.VERSION.SDK_INT >= 21) {

                // 先清理状态栏和导航栏的状态，然后强制显示状态栏和导航栏并设置flag模式
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                // 重新添加flag
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                // 设置状态栏颜色
                window.setStatusBarColor(Color.TRANSPARENT);
            }

            setStatusBarTextColor(window, b);

        }
    }

    /**
     * 设置字体颜色
     * @param window
     * @param b
     */
    public static void setStatusBarTextColor(Window window, boolean b) {
        //设置字体颜色，魅族和小米都分别提供了方法
        if (b) {
            FlymeSetStatusBarLightMode(window, true);
            MIUISetStatusBarLightMode(window, true);
            //其他机型没有提供对应方法 写这个就行
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * * 可以用来判断是否为Flyme用户
     * * @param window 需要设置的窗口
     * * @param dark 是否把状态栏字体及图标颜色设置为深色
     * * @return boolean 成功执行返回true *
     */
    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     * * @param window 需要设置的窗口
     * * @param dark 是否把状态栏字体及图标颜色设置为深色
     * * @return boolean 成功执行返回true
     */
    public static boolean MIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);
                }
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    /**
     * @author MrZ
     * @time 2017/11/22  14:23
     * @notes 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     *  @author MrZ
     *  @time 2017/10/16  13:53
     *  @notes 设置状态栏颜色
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}