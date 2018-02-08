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

### Concept 4 - App Customisation based on Audience
This is perhaps the most advanced and the most powerful feature that App Launch Service supports. Take Theme feature, for example, if you would like to create two audiences, subscribed users and unsubscribed users and tailor app customisation for each audience then user experience can be customised for different types of users.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
 - Let's create a feature
     - **ThemeGreen**
		 - property1  - dark_shade
		 - property2 -  light_shade
		 - property2 -  dark_shade_text

![Create feature](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/audience-based-content-delivery/images/feature.gif)

 - **Audience** - Earlier we had a single audience, called ThemeSegment. We will change this to include two separate audiences, SubscribedThemeSegment and UnsubscribedThemeSegment.
	 - 	Let's define an audience called, **Subscribed users**
	 - Attributes :
		- isSubscribed - true

![Create audience](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/audience-based-content-delivery/images/audience.gif)

 - **Engagement** - As defined in Concept 1 an engagement instantiates a feature by setting values. In this case, we will define an engagement.
	 - Create an Engagement, called - **Theme for subscribed users only**
		 - Initialize ThemeGreen feature with default colors
		 - Initialize Audience,
			 - Audience="**Subscribed users**"
![Create engagement](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/audience-based-content-delivery/images/engagement.gif)

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
String darkColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_ky6yn2pzh");
String lightColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_pb0l0g8y0");
String textColorForDarkBackground = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_nvf28igtq");
 ```

**Note** : Get the featureCode and propertyCode from the JSON file after downloading the feature from App Launch Console.

That's it - the app will load the Feature parameters during app initialization.

### FAST NEWS Screenshots
![FAST NEWS Concept1 ](https://github.com/ibm-cloud-applaunch/sample-android-fast-news/blob/audience-based-content-delivery/images/AudienceBased.png)

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
