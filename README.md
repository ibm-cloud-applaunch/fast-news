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

### Concept 3 - Vary App Customisation by percentage of Users
Often Developers would want to create multiple variants of Feature properties and apply them to a different percentage of users. For example, let's say we want to show different subscription plans to unsubscribed users. Fifty percentage of users should see one plan and the remaining fifty should see another one. The idea is to perform an A/B test on users to arrive at the more suitable question.

 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. We want this in-app message to be shown to all unsubscribed users then
	 - Let's define an audience called, **InAppSegment** 
	 - Attributes :
		- isSubscribed - false

![Create audience](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/a-b-test/images/create_audience.gif)

 - **Engagement** - Engagement allows you to create multiple Inapp instances (variations) by setting a percentage for each instance. For example,
	 - Variant 1 (50%)
		 - popUpText - “Subscribe for 3 months at 399/-”
		 - popUpYes  - “Yes”
		 - popUpNo - “No”
	 - Variant 2 (50%)
		 - popUpText - “Subscribe for 12 months at 999/-”
		 - popUpYes  - Yes
		 - popUpNo - “No”
 - Code - No change in the code since the App Launch Service handles the audience segmentation.	 

Once the above is defined in the Console, we have initialized the service in `initAppLaunchSDK()`

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
AppLaunch.getInstance().displayInAppMessages(NewsFeedActivity.this);
 ```

That's it - the app will load the In-app message during app initialization.

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


[IBM Mobile](mailto:yashsoni21@in.ibm.com)
