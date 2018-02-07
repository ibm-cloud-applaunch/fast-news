# App Launch Concepts and Examples - FAST NEWS

## Overview 

### App Launch
App Launch Service is an IBM Cloud Developer service that helps you control your application features. As a developer, you will need to be familiar with the concepts to best exploit the capability. In this document, you will learn the building blocks of the service and how each feature in the service will help solve your day-to-day problems using a simple news app - FAST NEWS.

### FAST NEWS
It's a news retrieval app. It has following features -
- Users login by giving UserId. Currently, app has three users - user1, user2 and user3.
- There is a `isSubscribed` boolean attribute associated with each user. For Yash `isSubscribed` is `true`, and for other two users, it's `false`.
- After login, user can see a list of news articles. These are fetched from NewsAPI.org
- Click on an article to read about it.
- Subscribed users can share that article.
- Subscribed users can listen to audio articles. For others it shows a subscription plan to avail the feature.

### Concept 3 - A/B Test
Often Developers would want to create multiple variants of Feature properties and apply them to a different percentage of users. For example, let's say we want to show different subscription plans to unsubscribed users. Fifty percentage of users should see one plan and the remaining fifty should see another one. The idea is to perform an A/B test on users to arrive at the more suitable question.

 - **Feature** - A feature is equivalent to a Java class where you define class members.
	 - Let's call it **SubscriptionPlan** 
	 - Define properties in this feature:
		- showPlan - true
		
![Create feature](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/a-b-test/images/feature.gif)

 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. We want this in-app message to be shown to all unsubscribed users then
	 - Let's define an audience called, **UnsubscribedUsers** 
	 - Attributes :
		- isSubscribed - false

![Create audience](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/a-b-test/images/audience.gif)

 - **Engagement** - Engagement allows you to create multiple feature instances and deliver them to different percent of users in the audience. We choose to deliver this feature's variant-1 to 50% of the "Unsubscribed User" audience and variant 2 will be targetting the other half.

![Create engagement](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/a-b-test/images/engagement.gif)

##### 1. Build Configuration Object

```
AppLaunchConfig appLaunchConfig = new AppLaunchConfig.Builder().eventFlushInterval(10).cacheExpiration(10).fetchPolicy(RefreshPolicy.REFRESH_ON_EVERY_START).deviceId(androidId).build();
```
- `androidId` : This is unique Android id.

##### 2. Build User Object

```
AppLaunchUser appLaunchUser = new AppLaunchUser.Builder().userId(userId).custom(AppCommons.FIELD_IS_SUBSCRIBED, isSubscribed())
.build();
```
- `userId` : The user to be registered
- `custom` : A boolean value to indicate whether he is subscribed or not.

##### 3. Initialize App Launch SDK

```
AppLaunch.getInstance().init(getApplication(), ICRegion.US_SOUTH, AppLaunchConstants.appGuid, AppLaunchConstants.clientSecret, appLaunchConfig, appLaunchUser, listener);
```

##### 4. Invoke SDK APIs

 ```
try {
    if (Boolean.valueOf(AppLaunch.getInstance().getPropertyOfFeature("feature_code_here", "property_code_here"))) {
	// what to do when the value is true?
    } else {
    	// optional false case
    }
} catch (Exception e){
    e.printStackTrace();
}
 ```
### FAST NEWS Screenshot
![Create engagement](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/a-b-test/images/AB.png)

### Metrics
A hidden gem inside App Launch Service is collecting metrics. App Launch Service allows extensive support to embed metric collection hooks, across all the concepts. These metrics will help you evaluate results of A/B testing, Feature performance, etc.
 
### Learn More

* Visit the **[IBM App Launch Docs](https://console-regional.ng.bluemix.net/docs/services/app-launch/index.html#gettingstartedtemplate)**. 

## License

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
