# App Launch Concepts and Examples - FAST NEWS

## Overview 

### App Launch
App Launch Service is an IBM Cloud Developer service that helps you control your application features. As a developer, you will need to be familiar with the concepts to best exploit the capability. In this document, you will learn the building blocks of the service and how each feature in the service will help solve your day-to-day problems using a simple news app - FAST NEWS.

### FAST NEWS
It's a news retrieval app. It has following features -
- Users login by giving UserId. Currently, app has three users - user1, user2 and user3.
- There is a `isSubscribed` boolean attribute associated with each user. For user1 `isSubscribed` is `true`, and for other two users, it's `false`.
- After login, user can see a list of news articles. These are fetched from NewsAPI.org
- Click on an article to read about it.
- Subscribed users can share that article.
- Subscribed users can listen to audio articles. For others it shows a subscription plan to avail the feature.

### Concept 2 - Toggle App Feature 
Let's say you would want to show `share` button to subscribed users. In the App Launch service Console you will configure this app attributes and later in your app you will add logic to implement it and will use the SDK APIs.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
	 - Let's call this feature - **ShareNews**
	 - Define properties in this feature
		 - property - enableShare

![Create feature](https://github.ibm.com/ibm-cloud-applaunch/sample-android-fast-news/blob/toggle-app-feature/images/create_feature.gif)

 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. Let's say you want this feature for all subscribed users then
	 - Let's define an audience called, **ShareSegment** 
	 - Attributes :
		- subscription - true

![Create audience](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/toggle-app-feature/images/create_audience.gif)

 - **Engagement** - An engagement is an instantiation of a Feature with properties initialized and attaching one of the pre-defined audiences. For our ShareNews feature, we will,
	 - Create an Engagement, called - **ShareNewsEngagement**
		 - Initialize ShareNews feature with,
			 - enableShare = "true"
		 - Initialize Audience,
			 - Audience = "ShareSegment"		 

![Create engagement](https://github.ibm.com/ibm-cloud-applaunch/sample-android-fast-news/blob/toggle-app-feature/images/create_engagement.gif)

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
boolean enableShareIcon = Boolean.valueOf(AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_mb1k9jf3n", propertyCode: "_o9d8tyg7h"));
 ```

**Note** : Get the featureCode and propertyCode from the JSON file after downloading the feature from App Launch Console.

That's it - the app will load the Feature parameters during app initialization.

### FAST NEWS Screenshots
![Feature Toggle Screenshots](https://github.ibm.com/ibm-cloud-applaunch/sample-android-fast-news/blob/toggle-app-feature/images/FeatureToggle.png)

### Metrics
A hidden gem inside App Launch Service is collecting metrics. App Launch Service allows extensive support to embed metric collection hooks, across all the concepts. These metrics will help you evaluate results of A/B testing, Feature performance, etc.
 
### App
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
