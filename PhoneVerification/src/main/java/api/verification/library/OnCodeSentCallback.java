package api.verification.library;

public interface OnCodeSentCallback {
    void onSuccessful(String OTP);

    void onFailed(VerificationException e);
}
