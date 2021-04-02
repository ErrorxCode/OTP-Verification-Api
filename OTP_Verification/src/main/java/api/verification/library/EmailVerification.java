package api.verification.library;

import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailVerification {

    /**
     * Through this server, you can not request OTP more then 100 times per hour. Total requesting limit is 200 for a day.
     */
    public static final int SERVER_MAIN = 1;
    /**
     * This is an alternative of SERVER_MAIN. only try if original one is not working.
     */
    public static final int SERVER_ALTERNATIVE = 2;
    /**
     * Through this server, you can only request 100 OTPs per day but 500 a month.
     */
    public static final int SERVER_MIRROR = -1;
    /**
     * This is an alternative of SERVER_MIRROR. only try if original one is not working.
     */
    public static final int SERVER_BACKUP = -2;
    private static String OTP;
    private static boolean EXPIRED = false;
    private static OnCodeSent onCodeSentListener;
    private static OnVerifyCode onVerifyCodeListener;


    protected static String GenerateOTP(int digits){
        String numbers = "0123456789";
        Random random = new Random();

        char[] otp = new char[digits];
        for (int i = 0; i < digits; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return new String(otp);
    }



    /**
     * Request OTP Verification from the selected server. The OTP is sent to the provided email address ,if sent successful then <code>SentSuccessfully()</code> method is invoked.
     * It gets EXPIRED after the <code>timeout_minutes</code>.
     *
     * @param Server  The Server to send mail
     * @param digits  The <code>No. of digits </code> of the generated OTP.
     * @param timeout_minutes  <code>Minutes</code> after which the OTP will get EXPIRED.
     * @param to  Receivers Address (email)
     */
    public static void RequestOTP(int Server, int digits, int timeout_minutes, String to, OnCodeSent onCodeSentListener) {
        String host;
        String port;
        String username;
        String password;
        String from;
        OTP = GenerateOTP(digits);
        EXPIRED = false;
        EmailVerification.onCodeSentListener = onCodeSentListener;
        switch (Server) {
            case SERVER_MIRROR:
                host = "smtp.elasticemail.com";
                port = "2525";
                username = "hackerinsiderahil@gmail.com";
                password = "D3FB44421D0D9E4AE3E1A330210EC2A8D4F8";
                from = "hackerinsiderahil@gmail.com";
                break;
            case SERVER_MAIN:
                host = "in-v3.mailjet.com";
                port = "25";
                username = "65d44b96b4349c60541935ee3c1f8329";
                password = "31d0427fae7a8667a1a87fb7639934ea";
                from = "hackerinsiderahil@gmail.com";
                break;
            case SERVER_ALTERNATIVE:
                host = "in-v3.mailjet.com";
                port = "25";
                username = "65f16479b1528ecb2fa20dc63122514e";
                password = "bef0c2fa86e96c51a5dc83e8862a8bcb";
                from = "peltobiyde@biyac.com";
                break;
            case SERVER_BACKUP:
                host = "smtp.elasticemail.com";
                port = "2525";
                username = "smtp_backup_elastic-mail@gmail.com";
                password = "13B8D6CBBBC5B2D928BFEA2D37DA17F1ACCD";
                from = "lakihod817@0pppp.com";
                break;
            default:
                throw new InvalidServerException("Server must be one of SERVER_MAIN or SERVER_MIRROR");
        }
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port",port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Verification code request");
            message.setText("Your Verification code is: " + OTP);
            Transport.send(message);
            if (onCodeSentListener != null)
                onCodeSentListener.SentSuccessful(OTP);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    OTP = null;
                    EXPIRED = true;
                }
            },1000*60*timeout_minutes);
        } catch (MessagingException e) {
            e.printStackTrace();
            if (onCodeSentListener != null)
                onCodeSentListener.SentFailed(e);
        }
    }


    /**
     * Verify the OTP & user provided code. If user provided code is matching with OTP then, <code>VerificationSuccessful()</code> method is invoked. The OTP which is sent to the provided email address
     * gets EXPIRED after the <code>timeout_minutes</code> & need to be requested again.
     *
     * @param systemCode  The Verification code sent by the server
     * @param userCode  The <code>Code</code> Entered by the user.
     */
    public static void VerifyCode(String userCode, String systemCode,OnVerifyCode onVerifyCodeListener){
        EmailVerification.onVerifyCodeListener = onVerifyCodeListener;
        if (systemCode == null) {
            throw new NullOTPException("Verification before requesting OTP. Are you stupid ? ");
        } else {
            if (userCode.equals(systemCode) && !EXPIRED) {
                onVerifyCodeListener.VerificationSuccessful();
            }
            else {
                onVerifyCodeListener.VerificationFailed(new MessagingException("Incorrect OTP"));
            }
        }
    }
}
