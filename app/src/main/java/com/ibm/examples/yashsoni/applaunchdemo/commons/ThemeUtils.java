package com.ibm.examples.yashsoni.applaunchdemo.commons;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.ibm.examples.yashsoni.applaunchdemo.R;
import com.ibm.mobile.applaunch.android.api.AppLaunch;
import com.ibm.mobile.applaunch.android.api.AppLaunchException;

/**
 * Created by yashsoni on 29/01/18.
 */

public class ThemeUtils {

    private static final String THEME_DARK = "theme_dark";
    private static final String THEME_LIGHT = "theme_light";
    private static final String THEME_TEXT_LIGHT = "theme_text_light";

    public static void getThemeFeature(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        try {
            String darkColor = AppLaunch.getInstance().getPropertyOfFeature("_eb2fbdn3u", "_ky6yn2pzh");
            String lightColor = AppLaunch.getInstance().getPropertyOfFeature("_eb2fbdn3u", "_pb0l0g8y0");
            String textColorForDarkBackground = AppLaunch.getInstance().getPropertyOfFeature("_eb2fbdn3u", "_nvf28igtq");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(THEME_DARK, darkColor);
            editor.putString(THEME_LIGHT, lightColor);
            editor.putString(THEME_TEXT_LIGHT, textColorForDarkBackground);
            editor.apply();
        } catch (AppLaunchException e) {
            e.printStackTrace();
        }
    }

    public static int getToolbarColor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(THEME_DARK)){
            String color = sharedPreferences.getString(THEME_DARK, "");
            return Color.parseColor(color);
        }
        return Color.WHITE;
    }

    public static int getToolbarTextColor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(THEME_TEXT_LIGHT)){
            String color = sharedPreferences.getString(THEME_TEXT_LIGHT, "");
            return Color.parseColor(color);
        }
        return Color.BLACK;
    }

    public static int getLightBackgroundColor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(THEME_LIGHT)){
            String color = sharedPreferences.getString(THEME_LIGHT, "");
            return Color.parseColor(color);
        }
        return Color.WHITE;
    }
}
