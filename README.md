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
	    implementation 'com.github.ErrorxCode:PhoneVerification:3.0'
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

# FAQs [?]

### Why PhoneVerification API over Firebase ?
| Firebase auth 							| PhoneVerification API						|
| --------------------------------------------------------------------- | ------------------------------------------------------------- |
| Firebase auth has request limit   					| Phoneverification has no request limit, you can use this unlimited times  |
| Firebase is slow, hard-2-use, & heavyweight   			| Phoneverification is fast, easy-2-use & lightweight			    |
| Firebase need account & internet to work 				| No account, No internet. PhoneVerification API does not require any kind of account. Also this works without interent |

These are the reason why you should prefer this library over firebase authentication. But not everytime, see why 👎

### Why Firebase over PhoneVerification API ?
| Firebase auth 							| PhoneVerification API						|
| --------------------------------------------------------------------- | ------------------------------------------------------------- |
| Firebase has console from where you can manage authentications	| Phoneverification has no console to manage authentication     |
| Firebase stores the mobile number which are been verified.		| Phoneverification does not store any kind of data, nor it has any server like firebase |

So every service has some pro's & con's. No one is perfect in the world. This totally depends on your need that which service would be best for you.

### So what should I choose ?
Ans. It totally depends on your needs. If you are using firebase database in your app, then I would suggest you to use firebase authentication.
If you want to store the mobile numbers or have to manage them, then go for firebase. If you don't need any of these feature, then you should choose PhoneVerification API.
 It is specialy made for those who don't want to increase their app size and don't want to write boilerplate code. This library would be best for you if you care about app & code size. Also here, you are getting unlimited request or bandwidth which no one will provide you for free.


## Support us
Support me to keep this library alive.
**If you like my hard work, please give this repo a star 🌟 & Nothing else.**
**Also check my other repos. Thank you !**
.

### Licence
```
MIT License

Copyright (c) 2021 Rahil khan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

[Join our telegram ](http://t.me/AndroDeveloperss)
