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

### Concept 4 - App Customisation by audiences
This is perhaps the most advanced and the most powerful feature that App Launch Service supports. Take Theme feature, for example, if you would like to create two audiences, subscribed Android users and unsubscribed Android users and tailor app customisation for each audience then user experience can be customised for different types of users.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
 - Let's create two features
     - **ThemeBlue**
		 - property1  - lightColor
		 - property2 - darkColor

     - **ThemeYellow**
         - property1 - lightColor
         - property2 - darkColor

 - **Audience** - Earlier we had a single audience, called ThemeSegment. We will change this to include two separate audiences, AndroidSubscribedThemeSegment and AndroidUnsubscribedThemeSegment.
	 - 	Let's define an audience called, **AndroidSubscribedThemeSegment** 
	 - Attributes :
	 	- platforms - Android
		- subscription - true
	 - 	Let's define another audience called, **AndroidUnsubscribedThemeSegment** 
	 - Attributes :
	 	- platforms - Android
		- subscription - false
 - Engagement - As defined in Concept 1 an engagement instantiates a feature by setting values. In this case, we will define two engagements.
	 - Create an Engagement, called - **AndroidThemeBlueEngagement**
		 - Initialize ThemeBlue feature with,
			 - lightColor = "#ADD8E6"
			 - darkColor = "#00008B"
		 - Initialize Audience,
			 - Audience="**AndroidSubscribedThemeSegment**"
	 - Create a second engagement, called **AndroidThemeYellowEngagement**
		 - Initialize ThemeYellow feature with,
			 - lightColor = "#FFFF99"
			 - darkColor = "#CCCC00"
		 - Initialize Audience,
			 - Audience="**AndroidUnsubscribedThemeSegment**"
			 
**Note** - you may create variants within each engagement, for example, within AndroidThemeYellowEngagement you may apply **Concept 3**.		 

Once the above is defined in the Console, we have initialized the service in `initAppLaunchSDK()`

##### 1. Build Configuration Object

```
AppLaunchConfig appLaunchConfig = new AppLaunchConfig.Builder().eventFlushInterval(10).cacheExpiration(10).fetchPolicy(RefreshPolicy.REFRESH_ON_EVERY_START).deviceId(androidId).build();
```
- `androidId` : This is unique Android id.

##### 2. Build User Object

```
AppLaunchUser appLaunchUser = new AppLaunchUser.Builder().userId(userId).custom(AppCommons.FIELD_SUBSCRIPTION, isSubscribed())
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
String darkColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_r0m5vfc81", propertyCode: "_74lwhwe0l");
String lightColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_r0m5vfc81", propertyCode: "_39qu6dahb");
 ```

**Note** : Get the featureCode and propertyCode from the JSON file after downloading the feature from App Launch Console.

That's it - the app will load the Feature parameters during app initialization.
 
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
