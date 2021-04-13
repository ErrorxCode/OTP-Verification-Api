# **OTP Verification Library**

<p align="left">
  <a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
  <a href="#"><img alt="Languages-Java" src="https://img.shields.io/badge/Language-Java-1DA1F2?style=flat-square&logo=java"></a>
  <a href="#"><img alt="PRs" src="https://img.shields.io/badge/By-Rahil-red"></a>
  <a href="#"><img alt="PRs" src="https://jitpack.io/v/ErrorxCode/OTP-Verification-Api.svg"></a>
</p>

***Most Easiest & lightweight library you had ever seen. Now verify Email's / Phone using this API. Just one line & you are all done, Although this API has some limitation***. 

## Normal Server
### Pros
- Free & No account needed
- 2 Backup servers, in case of emergency or quota reached.

### Cons / Limitations 
- You can request OTP via mail only limited number of times per day
- SMS Verification is only for Android users & carrier charge may apply.
- The server will only accepts limited no. of OTP request made via email. doesn't matter who is using it, it will be counted by the server for everyone 🤐

***I will fix these issue in next release, if I get enough support***.

## Premium servers
#### Pros & Features:
- Your personal server, hence it will be only used by you.
- Free, Fast & Genuine
- It will only count your request as your daily limit or quata.
- Higher limits & quata, hence more OTPs requests a day.
- Free 700 + email verification
- 3 Backup Server available.

#### Usage:
```
        EmailVerification.setCustomServer(SERVER_ADDRESS,PORT,USERNAME,PASSWORD); // All these will be provided once you purchase premium server.
        EmailVerification.RequestOTP(EmailVerification.SERVER_CUSTOM, "xxxx", "xxxx@gmail.com", new OnCodeSent() {
            @Override
            public void SentSuccessful(String OTP) {
                // Do what you want
            }

            @Override
            public void SentFailed(Exception e) {
                // may be because, email is invalid or disposable
            }
        });

```
#### How to get ?
Contact me & I will provide you premium server within 48 hours. Contacting options:-
- [Telegram ](http://t.me/ErrorxCode)
- [Instagram](https://www.instagram.com/x__coder__x/)
- [Youtube comments](https://youtu.be/hO7CE1Q0AI0) (Recommended)
- [Email at hackerinsiderahil@gmail.com](https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox?compose=GTvVlcSHxjTVLBKvrdShskXbmtQkKVHsBRwnjlvJDRTmRkcZCZKnQBhMQQpNGtbqrXlDxJGrtrPML) (Late reply)

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

## Tutorial
**To request OTP using E-mail**

```
EmailVerification.RequestOTP(SERVER_MAIN, 4, 5, "senderAddress@domain.com", new OnCodeSent() {
            @Override
            public void SentSuccessful(String OTP) {
                // Store this OTP in a Variable
            }

            @Override
            public void SentFailed(MessagingException e) {
                // handle this error and show error message to user
            }
        });
```

**To request OTP using SMS** ( [!] for only android )

```
PhoneVerification.RequestOTP(4,5,USER_MOBILE_NUMBER);
```
**To verify code**
```
EmailVerification/PhoneVerification.VerifyCode(USER_ENTERED_CODE, SYSTEM_CODE, new OnVerifyCode() {
            @Override
            public void VerificationSuccessful() {
                // OTP is correct & verified
            }

            @Override
            public void VerificationFailed(Exception e) {
                // OTP is incorrect & not verified
            }
        });
```

### Support us
**If you like my hard work, please give this repo a star 🌟 & Nothing else.**
**Also check my other repos. Thank you !**


[Join our telegram ](http://t.me/TeamDestroyerss)
