# **OTP Verification Library**

***Most Easiest & lightweight library you had ever seen. Now verify Email's / Phone using this API. Just one line & you are all done, Although this API has some limitation***. 


### Pros
- Lightweight, hence easy to use
- Free & No account needed
- 3 Backup servers, in case of emergency or quota reached.
- Require Almost zero knowledge, just one word to wright.
- Open source, hence anyone can contribute

### Cons / Limitations 
- You can request OTP via mail only limited number of times per day
- SMS Verification is only for Android users.
- The server will only accepts limited no. of OTP request made via email. doesn't matter who is using it, it will be counted by the server for everyone 🤐

***I will fix these issue in next release, if I get enough support***.

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
	     implementation 'com.github.ErrorxCode:OTP-Verification-Api:1.5'
}
```

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
