package api.verification.library;

import android.telephony.SmsManager;
import java.util.Timer;
import java.util.TimerTask;

public class PhoneVerification {
    private static String OTP;
    private static boolean EXPIRED = false;
    private static OnCodeSent onCodeSentListener;
    private static OnVerifyCode onVerifyCodeListener;



    /**
     * Request OTP Verification. The OTP is sent to the provided Mobile number ,if sent successful then <code>SentSuccessfully()</code> method is invoked.
     * OTP gets EXPIRED after the <code>timeout_minutes</code>. This use your own mobile number as senders address for requesting OTP hence, carrier charge may apply. Moreover it requires <code>SEND_SMS</code>
     * permission.
     *
     * @param digits  The <code>No. of digits </code> of the generated OTP.
     * @param timeout_minutes  <code>Minutes</code> after which the OTP will get EXPIRED.
     * @param to  Receivers Address (email)
     */
    public static String RequestOTP(int digits,int timeout_minutes,String to){
        EXPIRED = false;
        OTP = EmailVerification.GenerateOTP(digits);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(to, null,"Your Verification Code is : " + OTP, null, null);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                OTP = null;
                EXPIRED = true;
            }
        },1000*60*timeout_minutes);
        return OTP;
    }

    /**
     * Verify the OTP & user provided code. If user provided code is matching with OTP then, <code>VerificationSuccessful()</code> method is invoked. The OTP which is sent to the provided mobile number
     * gets EXPIRED after the <code>timeout_minutes</code> & need to be requested again.
     *
     * @param systemCode  The Verification code sent by the server
     * @param userCode  The <code>Code</code> Entered by the user.
     */
    public static void VerifyCode(String userCode, String systemCode,OnVerifyCode onVerifyCodeListener){
        PhoneVerification.onVerifyCodeListener = onVerifyCodeListener;
        if (systemCode == null) {
            throw new NullOTPException("Verification before requesting OTP. Are you sure ? , check if OTP is sent or not !");
        } else {
            if (userCode.equals(systemCode) && !EXPIRED) {
                if (onVerifyCodeListener != null) onVerifyCodeListener.VerificationSuccessful();
            }
            else {
                if (onVerifyCodeListener != null) onVerifyCodeListener.VerificationFailed(new Exception("verification failed. OTP incorrect ? , may be !"));
            }
        }
    }
}