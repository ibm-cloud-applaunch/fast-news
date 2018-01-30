package com.ibm.examples.yashsoni.applaunchdemo.commons;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.mobile.applaunch.android.api.AppLaunch;
import com.ibm.mobile.applaunch.android.api.AppLaunchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yashsoni on 29/01/18.
 */

public class ThemeUtils {

    private static final String TEAL = "teal";
    private static final String YELLOW = "yellow";
    private static final String THEME = "theme";

    private static Map<String, String> darkShadeColors;
    private static Map<String, String> lightShadeColors;

    static {
        darkShadeColors = new HashMap<>();
        lightShadeColors = new HashMap<>();

        // teal colors
        darkShadeColors.put(TEAL, "#26A69A");
        lightShadeColors.put(TEAL, "#80CBC4");

        // yellow colors
        darkShadeColors.put(YELLOW, "#FFEB3B");
        lightShadeColors.put(YELLOW, "#FFF9C4");
    }

    public static void getThemeFeature(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        try {
            String color = AppLaunch.getInstance().getPropertyOfFeature("_dnhfgsseg", "_lkfdgisd1");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(THEME, color);
            editor.apply();
        } catch (AppLaunchException e) {
            e.printStackTrace();
        }
    }

    public static int getToolbarColor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(THEME)){
            String color = sharedPreferences.getString(THEME, "");
            switch (color){
                case TEAL:
                    return Color.parseColor(darkShadeColors.get(TEAL));
                case YELLOW:
                    return Color.parseColor(darkShadeColors.get(YELLOW));
                default:
                    return Color.WHITE;
            }
        }
        return Color.WHITE;
    }

    public static int getLightBackgroundColor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(THEME)){
            String color = sharedPreferences.getString(THEME, "");
            switch (color){
                case TEAL:
                    return Color.parseColor(lightShadeColors.get(TEAL));
                case YELLOW:
                    return Color.parseColor(lightShadeColors.get(YELLOW));
                default:
                    return Color.WHITE;
            }
        }
        return Color.WHITE;
    }
}
