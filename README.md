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

<<<<<<< HEAD
### Concept 1 - Remote App Customisation
A most common use case is where an app developer would want to change app background, a button text or certain widget placement in the app once after the app is published to the Appstore. Another Appstore publish might be an overkill, hence developers would prefer an over-the-air update. This gives developers a much-needed flexibility to manipulate app behavior.

Let's say you would want to change the app's background. In the App Launch service Console you will configure this app attributes and later in your app you will add logic to implement it and will use the SDK APIs.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
	 - Let's call this feature - **ThemeBlue**
	 - Define properties in this feature
		 - property1  - toolbarTextColor
		 - property2  - toolbarBackgroundColor
		 - property3  - lightBackgroundColor

![Create feature](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/app-customisation/images/create_feature.gif)

 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. Let's say you want this feature for all Android users then 
	 - Let's define an audience called, **ThemeSegment** 
	 - Set its value to 'AllAndroidUsers'

![Create audience](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/app-customisation/images/create_audience.gif)

 - **Engagement** - An engagement is an instantiation of a Feature with properties initialized and attaching one of the pre-defined audiences. For our ThemeBlue feature, we will,
	 - Create an Engagement, called - **ThemeBlueEngagement**
		 - Initialize ThemeBlue feature with,
			 - toolbarTextColor = "#484848"
			 - toolbarBackgroundColor = "#4fc3f7"
			 - lightBackgroundColor = "#e1f5fe"
		 - Initialize Audience,
			 - Audience = "ThemeSegment"

![Create engagement](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/app-customisation/images/create_engagement.gif)			 
=======
### Concept 4 - App Customisation by audiences
This is perhaps the most advanced and the most powerful feature that App Launch Service supports. Take Theme feature, for example, if you would like to create two audiences, subscribed users and unsubscribed users and tailor app customisation for each audience then user experience can be customised for different types of users.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
 - Let's create two features
     - **ThemeBlue**
		 - property1  - lightColor
		 - property2 - darkColor

     - **ThemeYellow**
         - property1 - lightColor
         - property2 - darkColor

![Create feature](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/audience-based-content-delivery/images/create_feature.gif)

 - **Audience** - Earlier we had a single audience, called ThemeSegment. We will change this to include two separate audiences, SubscribedThemeSegment and UnsubscribedThemeSegment.
	 - 	Let's define an audience called, **SubscribedThemeSegment**
	 - Attributes :
		- isSubscribed - true
	 - 	Let's define another audience called, **UnsubscribedThemeSegment**
	 - Attributes :
		- isSubscribed - false

![Create audience](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/audience-based-content-delivery/images/create_audience.gif)

 - Engagement - As defined in Concept 1 an engagement instantiates a feature by setting values. In this case, we will define two engagements.
	 - Create an Engagement, called - **ThemeBlueEngagement**
		 - Initialize ThemeBlue feature with,
			 - lightColor = "#ADD8E6"
			 - darkColor = "#00008B"
		 - Initialize Audience,
			 - Audience="**SubscribedThemeSegment**"
	 - Create a second engagement, called **ThemeYellowEngagement**
		 - Initialize ThemeYellow feature with,
			 - lightColor = "#FFFF99"
			 - darkColor = "#CCCC00"
		 - Initialize Audience,
			 - Audience="**UnsubscribedThemeSegment**"

![Create engagement](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/audience-based-content-delivery/images/create_engagement.gif)

**Note** - you may create variants within each engagement, for example, within ThemeYellowEngagement you may apply **Concept 3**.
>>>>>>> origin/audience-based-content-delivery

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
<<<<<<< HEAD
String darkColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_ky6yn2pzh");
String lightColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_pb0l0g8y0");
String textColorForDarkBackground = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_eb2fbdn3u", propertyCode: "_nvf28igtq");
=======
String darkColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_r0m5vfc81", propertyCode: "_74lwhwe0l");
String lightColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_r0m5vfc81", propertyCode: "_39qu6dahb");
>>>>>>> origin/audience-based-content-delivery
 ```

**Note** : Get the featureCode and propertyCode from the JSON file after downloading the feature from App Launch Console.

That's it - the app will load the Feature parameters during app initialization.

<<<<<<< HEAD
### FAST NEWS Screenshots
![FAST NEWS Concept1 ](https://github.ibm.com/yasoni12/AppLaunchDemo/blob/app-customisation/images/concept1_screenshots.png)

### Metrics
A hidden gem inside App Launch Service is collecting metrics. App Launch Service allows extensive support to embed metric collection hooks, across all the concepts. These metrics will help you evaluate results of A/B testing, Feature performance, etc.
 
=======
### Metrics
A hidden gem inside App Launch Service is collecting metrics. App Launch Service allows extensive support to embed metric collection hooks, across all the concepts. These metrics will help you evaluate results of A/B testing, Feature performance, etc.

>>>>>>> origin/audience-based-content-delivery
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
