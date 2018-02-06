package com.ibm.applaunch.samples.commons;

import android.os.Build;

import com.ibm.applaunch.samples.models.NewsFeedModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by yashsoni on 22/01/18.
 */

public class AppCommons {
//    users and their subscription status
    private static final String[] users = new String[]{"user1", "user2", "user3"};
    private static final Boolean[] userSubscription = new Boolean[]{true, false, false};

//    Local cache helper variables
    public static final String NEWS_FEED_DETAILS = "news_feed_details";
    public static final String LOGGED_IN_USER = "logged_in_user";

//    News API
    private static final String API_KEY_NEWS_FEED = "57e52756282748ccb0f6699e02219858";
    public static final String URL_FOR_NEWS_FEED = "https://newsapi.org/v2/top-headlines?sources=the-times-of-india&apiKey=" + API_KEY_NEWS_FEED;

//    Fields for registration
    public static final String FIELD_OS_VERSION = "osVersion";
    public static final String FIELD_IS_SUBSCRIBED = "isSubscribed";


    private static ArrayList<String> title = new ArrayList<>();
    private static ArrayList<String> desc = new ArrayList<>();
    private static ArrayList<String> author = new ArrayList<>();

    private static ArrayList<NewsFeedModel> getDataList() {
        ArrayList<NewsFeedModel> dataList = new ArrayList<>();
        populateData();
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());
        dataList.add(getRandomFeedModel());

        dataList.get(dataList.size() - 1).isAudioAvailable = true;
        dataList.get(dataList.size() - 2).isAudioAvailable = true;
        return dataList;
    }

    private static void populateData() {
        title.add("Bruno Mars' album '24K Magic' wins 7 Grammy Awards");
        desc.add("The album '24K Magic' by singer Bruno Mars won seven awards including Album Of The Year and Best R&B Album at the 60th Grammy Awards this year. His single 'That's What I Like' was named Song Of The Year, Best R&B Performance and Best R&B Song. Further, his song '24K Magic' won the Grammy for Record of The Year.");
        author.add("Prashanti Moktan");

        title.add("RPG Group is hiring in the most unconventional way");
        desc.add("RPG Group just launched a microsite that gives B-Schools students the opportunity to interview with them for a job directly through their phones or desktop. An unconventional way to find new recruits. All they need to do is record a video about themselves and the most impressive entries get a chance to work with the Group. Here's the link: campus.rpggroup.com");
        author.add("Roshan Gupta");

        title.add("Kohli surpasses Lara in all-time Test rating points list");
        desc.add("Following his knocks of 54 and 41 in the last South Africa Test, India captain Virat Kohli has moved past legend Brian Lara in the all-time Test rating points list. With 912 points, current world number two Test batsman Kohli moved from 31st position to 26th position, leapfrogging Lara (911), Kevin Pietersen (909), Hashim Amla (907) and Michael Clarke (900).");
        author.add("Anmol Sharma");

        title.add("US prosecutor to investigate firm selling online followers");
        desc.add("The New York attorney general, Eric Schneiderman, will investigate Devumi, a company that allegedly sold millions of fake social media followers. This follows a report by The New York Times, which said at least 55,000 of Devumi's accounts used personal details of real Twitter users. It said famous actors, television presenters and entrepreneurs were among those who bought Devumi followers.");
        author.add("Krishna Veera Vanamali");

        title.add("If selling pakoda is job, so is begging: Ex-FM slams PM Modi");
        desc.add("Former Finance Minister and Congress leader P Chidambaram has said \"if selling pakodas is a job, then so is begging\". This was in response to PM Narendra Modi's recent remark, \"If a person selling pakodas earns ₹200 at the end of the day, will it be considered employment or not?\" Chidambaram said the NDA government is \"clueless\" about job creation.");
        author.add("Ayushi Ahluwalia");

        title.add("Morarji Desai presented the most number of Budgets");
        desc.add("Former Finance Minister and Prime Minister Morarji Desai presented the maximum number of Budgets at 10 so far, with two of them being Interim Budgets. He is also the only Finance Minister to present Budgets on his birthday, on February 29 in 1964 and 1968. Former Finance Minister P Chidambaram presented the second highest number of Budgets, at nine.");
        author.add("Krishna Veera Vanamali");
    }

    private static NewsFeedModel getRandomFeedModel() {
        NewsFeedModel newsFeedModel = new NewsFeedModel();
        newsFeedModel.title = title.remove(0);
        newsFeedModel.desc = desc.remove(0);
        newsFeedModel.author = author.remove(0);
        newsFeedModel.imageUrl = "https://pbs.twimg.com/profile_images/937505665454448640/R8r4vQ-U_400x400.jpg";
        return newsFeedModel;
    }

    public static ArrayList<NewsFeedModel> getNewsFeed(String response) {
        if (response == null)
            return getDataList();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArrayArticles = jsonObject.getJSONArray("articles");
            int size = jsonObject.getInt("totalResults");
            ArrayList<NewsFeedModel> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                JSONObject article = jsonArrayArticles.getJSONObject(i);
                NewsFeedModel feedModel = new NewsFeedModel();
                feedModel.imageUrl = article.getString("urlToImage");
                feedModel.author = article.getString("author");
                feedModel.title = article.getString("title");
                feedModel.desc = article.getString("description");
                list.add(feedModel);
            }
            list.get(list.size() - 1).isAudioAvailable = true;
            list.get(list.size() - 2).isAudioAvailable = true;
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
            return getDataList();
        }
    }

    public static boolean isValidUser(String userId) {
        for (String user : users) {
            if (user.equalsIgnoreCase(userId)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubscribedUser(String userId){
        for(int i=0; i<users.length; i++){
            if(users[i].equalsIgnoreCase(userId)){
                return userSubscription[i];
            }
        }
        return false;
    }

    public static String getDeviceOSVersion() {
        int versionCode = Build.VERSION.SDK_INT;
        switch (versionCode){
            case 21:
            case 22:
                return "LOLLIPOP";
            case 23:
                return "MARSHMALLOW";
            case 24:
            case 25:
                return "NOUGAT";
            case 26:
            case 27:
                return "OREO";
            default:
                return "LOLLIPOP";
        }
    }
}
