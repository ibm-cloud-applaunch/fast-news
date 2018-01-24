package com.ibm.examples.yashsoni.applaunchdemo.commons;

import com.ibm.examples.yashsoni.applaunchdemo.models.NewsFeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yashsoni on 22/01/18.
 */

public class AppCommons {
    public static final String[] users = new String[] { "Yash", "Surbhi" };
    public static final Boolean[] userSubscription = new Boolean[] { true, false };
    public static final String NEWS_FEED_DETAILS = "news_feed_details";
    public static final String LOGGED_IN_USER = "logged_in_user";
    public static final String FEILD_IS_SUBSCRIBED = "isSubscribed";
    public static final String FEILD_SUBSCRIPTION = "subscription";

    private static List<NewsFeedModel> dataList;

    public static List<NewsFeedModel> getDataList() {
        dataList = new ArrayList<>();
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        return dataList;
    }

    private static NewsFeedModel getRandomFeedModel(){
        NewsFeedModel newsFeedModel = new NewsFeedModel();
        newsFeedModel.title = "Biggest News Of The Year";
        newsFeedModel.desc = "Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!Trust me! This is the biggest news ever. Even Trump is shocked! Trust me! This is the biggest news ever. Even Trump is shocked!";
        newsFeedModel.author = "- Some Cool Guy";
        newsFeedModel.imageUrl = "https://pbs.twimg.com/profile_images/937505665454448640/R8r4vQ-U_400x400.jpg";
        newsFeedModel.isAudioAvailable = (Math.random() <= 0.5d);
        return newsFeedModel;
    }
}
