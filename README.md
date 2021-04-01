# **OTP Verification Library**

***Most Easiest & lightweight library you had ever seen. Now verify Email's / Phone using this API. Just one line & you are all done, Although this API has some limitation***. 


### Pros
- Lightweight, hence easy to use
- Free & No account needed
- Backup/Mirror servers, in case of emergency.
- Require Almost zero knowledge, just one word to wright.
- Open source, hence anyone can contribute

### Cons / Limitations [only email]
- By SERVER_MAIN, you can request only 100 OTPs/hour & 200 a day.
- By SERVER_MIRROR, you can send 100 OTP a day but 500/month.
- SMS Verification is only for Android users.

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
	     implementation 'com.github.ErrorxCode:OTP-Verification-Api:1.0'
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
