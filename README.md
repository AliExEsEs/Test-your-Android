# Test-your-Android

APP NAME ~ 
	Test your Android

DESCRIPTION ~
	This app is an android application that accesses the various hardware and software components of an Android device.
	The device accesses the device sensors like the Gps and Bluetooth many more. The app will show the results if 
	the device has the features or let the the user know about the missing components.

DEPENDENCIES ~
	The app uses various dependencies to achieve its functionalities. The dependencies used are listed below.

	1: // Biometric Dependency 
         implementation "androidx.biometric:biometric:1.1.0"

    	2: // permissions
    	   implementation 'pub.devrel:easypermissions:3.0.0'

    	3: // Glide
    	   implementation 'com.github.bumptech.glide:glide:4.14.2'
    	   annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'

    	4: //Google Services
    	   implementation 'com.google.android.gms:play-services-location:21.0.0'

    	5: //Youtube player dependency
    	   implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0'

PERMISSIONS ~
	The app requires basic permissions from the user since these hardware cant be accessed without the user permission and knowledge.
	These permissions were added to the Manifest.xml file. The list is given below.
	
	<uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
      <uses-permission android:name="android.permission.BLUETOOTH"/>
      <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
      <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
      <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
      <uses-permission android:name="android.permission.INTERNET" />
      <uses-permission android:name="android.permission.CAMERA" />
      <uses-permission android:name="android.permission.FLASHLIGHT" />
      <uses-permission android:name="android.permission.VIBRATE" />
      <uses-permission android:name="android.permission.RECORD_AUDIO"/>

WORKING STATUS ~
	Working Completly fine.

KNOWN ISSUES OR BUGS ~
	As of writting this README file, there were no encountered crashes caused due to code logic. 
	
	P.S - The app/functionality stops working if the user denies the request permission more that twice. The issue here is that the 
	Permission request promp dialog box doesn't show after two times. It is caused due to Google policy after Android 11. In Andorid 11
	and later versions the request can only be asked twice from the user. after that the user would have to manually go to the settings to allow 
	permissions.

SUGGESTIONS AND REVIEWS ~

	clientmail091@gmail.com

	I would love to hear from you wherever you are in the world :)


