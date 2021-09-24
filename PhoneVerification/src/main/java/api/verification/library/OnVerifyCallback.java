package api.verification.library;

public interface OnVerifyCallback{
    void onSuccessful();
    void onFailed(VerificationException e);
}