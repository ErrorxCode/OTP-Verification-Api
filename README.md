# **PhoneVerification API library**

<p align="left">
  <a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
  <a href="#"><img alt="Languages-Java" src="https://img.shields.io/badge/Language-Java-1DA1F2?style=flat-square&logo=java"></a>
  <a href="https://www.instagram.com/x__coder__x/"><img alt="Instagram - x__coder__" src="https://img.shields.io/badge/Instagram-x____coder____x-lightgrey"></a>
  <a href="#"><img alt="GitHub Repo stars" src="https://img.shields.io/github/stars/ErrorxCode/OTP-Verification-Api?style=social"></a>
  </p>
  
***This is an easy-2-use API for implementing OTP verification in your app. You don't need to write boilerplate code for requesting or verifying the code.
  Just need to call respective method with 2-3 arguments and rest the library will manage. The main + point of this API is that it does not require internet to run, instead
  it requires sim subscription to send otp***.

## Pros & Features:
- Your personal server So, No limit, No bandwidth.
- Free, Fast, lightweight & small in size (only 9.72 KB)
- It doesn't even require internet

***The only con is that***, this library will add few permission to your manifest, SEND_SMS for sure and other 2 (RECEIVE_SMS and READ_PHONE_STATE) depends on your needs.

**Note: This api is based on a assumption that, the user has at least one sim with active subscription**.


## Implimentation
Add maven to your root build.gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency
```
dependencies {
	    implementation 'com.github.ErrorxCode:OTP-Verification-Api:Tag'
}
```
[![](https://jitpack.io/v/ErrorxCode/OTP-Verification-Api.svg)](https://jitpack.io/#ErrorxCode/OTP-Verification-Api)



## Usage:
First make sure your app has the following permission :- 
```xml
<uses-permission android:name="android.permission.SEND_SMS" />		// Required to use this library
<uses-permission android:name="android.permission.READ_PHONE_STATE" /> 	// Only, if you use requestVerificationImplicit()
<uses-permission android:name="android.permission.RECEIVE_SMS" />  	// Only if you use startAutoVerification()
```
**To send verification code to user** :
```java
PhoneVerification.requestVerificationImplicit(this, "92148xxxxx", new OnCodeSentCallback() {
            @Override
            public void onSuccessful(String OTP) {
                // OTP is the code which is successfully sent
            }

            @Override
            public void onFailed(VerificationException e) {
                // OTP is not sent, hence we need to see the reason
                e.printStackTrace();
            }
        });
```

You can also use `PhoneVerification.requestVerificationExplicit()`. The only difference is that, Explicit one will show a promt to select the sim if there is no default sim set for sending sms, While Implicit will automatically use the 1st sim.`

**To verify the OTP** :
```java
PhoneVerification.verifyCode(otpView.getText().toString(), new OnVerifyCallback() {
            @Override
            public void onSuccessful() {
                // Verification successful, OTP match !
            }

            @Override
            public void onFailed(VerificationException e) {
                // Verification failed, we need to see the exception
            }
        });
```
You can also use `PhoneVerification.startAutoVerification()` to automatically verify the code. **Note that, this method must be called befour requesting verification code**.

# Firebase vs this

### Support us
**If you like my hard work, please give this repo a star 🌟 & Nothing else.**
**Also check my other repos. Thank you !**


[Join our telegram ](http://t.me/AndroDeveloperss)
