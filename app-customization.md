# App Launch Concepts and Examples - FAST NEWS

## Overview
App Launch Service is an IBM Cloud Developer service that helps you control your application features. As a developer, you will need to be familiar with the concepts to best exploit the capability. In this document, you will learn the building blocks of the service and how each feature in the service will help solve your day-to-day problems using a simple news app.

### Concept 1 - Remote App Customisation
A most common use case is where an app developer would want to change app background, a button text or certain widget placement in the app once after the app is published to the Appstore. Another Appstore publish might be an overkill, hence developers would prefer an over-the-air update. This gives developers a much-needed flexibility to manipulate app behavior.

Let's say you would want to change the app's background. In the App Launch service Console you will configure this app attributes and later in your app you will add logic to implement it and will use the SDK APIs.

 - **Feature** - A feature is equivalent to a Java class where you define class members. 
	 - Let's call this feature - **ThemeBlue**
	 - Define properties in this feature
		 - property  - backgroundColor
 - **Audience** - An audience is a collection of attributes that define the characteristics of an audience segment. Let's say you want this feature for all Android users then 
	 - Let's define an audience called, **ThemeSegment** 
	 - Set its value to 'AllAndroidUsers'
 - **Engagement** - An engagement is an instantiation of a Feature with properties initialized and attaching one of the pre-defined audiences. For our ThemeBlue feature, we will,
	 - Create an Engagement, called - **ThemeBlueEngagement**
		 - Initialize ThemeBlue feature with,
			 - backgroundColor = "#0000FF"
		 - Initialize Audience,
			 - Audience="ThemeSegment"

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
get feature API in `ThemeUtils` class in `getThemeFeature` method,
 ```
String darkColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_48836r1x5", propertyCode: "_n7ic7lt1n");
String lightColor = AppLaunch.getInstance().getPropertyOfFeature(featureCode: "_48836r1x5", propertyCode: "_3y6nuefy7");
 ```
and called this method in `NewsFeedActivity` class in `onSuccess` method,
 ```
 ThemeUtils.getThemeFeature(this);
 ```

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
