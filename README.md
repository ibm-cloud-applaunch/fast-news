# App Launch Concepts and Examples - FAST NEWS

## Overview

### App Launch
App Launch Service is an IBM Cloud Developer service that helps you control your application features. As a developer, you will need to be familiar with the concepts to best exploit the capability. In this document, you will learn the building blocks of the service and how each feature in the service will help solve your day-to-day problems using a simple news app - FAST NEWS.

### FAST NEWS
It's a news retrieval app. It has following features -
- Users login by giving UserId. Currently, app has three users - Yash, Surbhi and Charan.
- There is a `subscription` boolean attribute associated with each user. For Yash `subscription` is `true`, and for other two users, it's `false`.
- After login, user can see a list of news articles. These are fetched from NewsAPI.org
- Click on an article to read about it.
- Subscribed users can share that article.
- Subscribed users can listen to audio articles. For others it shows a subscription plan to avail the feature.

### Concept 5 - Device based Content Delivery
One important use case is where an app developer would want to customise app based on device screen size, OS, etc.
Let's sat FAST NEWS has revamped their app according to the recent Android OS Oreo and they conduct a survey to ask people if they like the new version of app or not.

 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. Let's say you want this feature for all Oreo users then
	 - Let's define an audience called, **SurveySegment**
	 - Attributes :
	 	- OS Version - 8.1.0

 - **Engagement** - An engagement is an instantiation of a Feature with properties initialized and attaching one of the pre-defined audiences.
	 - Create an Engagement, called - **SurveyEngagement**
		 - Initialize In-app message with,
			 - popUpText = "Enjoying the new look of app?"
			 - popUpYes = "Absolutely"
			 - popUpNo = "Nah"
		 - Initialize Audience,
			 - Audience = "SurveySegment"

Once the above is defined in the Console, we have initialized the service in `initAppLaunchSDK()`

##### 1. Build Configuration Object

```
AppLaunchConfig appLaunchConfig = new AppLaunchConfig.Builder().eventFlushInterval(10).cacheExpiration(10).fetchPolicy(RefreshPolicy.REFRESH_ON_EVERY_START).deviceId(androidId).build();
```
- `androidId` : This is unique Android id.

##### 2. Build User Object

```
AppLaunchUser appLaunchUser = new AppLaunchUser.Builder().userId(userId).custom(AppCommons.FIELD_OS_VERSION, getOSVersion())
.build();
```
- `userId` : The user to be registered
- `custom` : A string value which indicates the OS version.

##### 3. Initialize App Launch SDK

```
AppLaunch.getInstance().init(getApplication(), ICRegion.US_SOUTH, AppLaunchConstants.appGuid, AppLaunchConstants.clientSecret, appLaunchConfig, appLaunchUser, listener);
```

##### 4. Invoke SDK APIs

 ```
AppLaunch.getInstance().displayInAppMessages(NewsFeedActivity.this);
 ```

That's it - the app will load the In-app message during app initialization.

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
