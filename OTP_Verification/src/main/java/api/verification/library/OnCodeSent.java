package api.verification.library;

import javax.mail.MessagingException;

public interface OnCodeSent {
    void SentSuccessful(String OTP);
    void SentFailed(Exception e);
}

interface OnVerifyCode{
    void VerificationSuccessful();
    void VerificationFailed(Exception e);
}