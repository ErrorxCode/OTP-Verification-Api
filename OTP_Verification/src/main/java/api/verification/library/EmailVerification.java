package api.verification.library;

import java.io.UnsupportedEncodingException;
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
     * This server allows you to request 500+ OTP per day.
     */
    public static final int SERVER_MAIN = 1;
    /**
     * This is an alternative of SERVER_MAIN. only try if original one is not working.
     */
    public static final int SERVER_ALTERNATIVE = 2;
    /**
     * This server allows you to request only 100 OTPs a day.
     */
    public static final int SERVER_MIRROR = -1;
    /**
     * This is the custom server, this will only work if you have set it using <code>setCustomServer()</code> method.
     * You can get this by purchasing premium Server.
     */
    public static final int SERVER_CUSTOM = 0;
    private static String OTP;
    private static boolean EXPIRED = false;
    private static OnCodeSent onCodeSentListener;
    private static OnVerifyCode onVerifyCodeListener;
    private static String CUSTOM_HOST;
    private static String CUSTOM_PORT;
    private static String CUSTOM_USERNAME;
    private static String CUSTOM_PASSWORD;


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
     * OTP gets EXPIRED after 5 minutes.
     *
     * @param Server  The Server to send mail
     * @param to  Receivers Address (email)
     */
    public static void RequestOTP(int Server,String name, String to, OnCodeSent onCodeSentListener) {
        String host;
        String port;
        String username;
        String password;
        OTP = GenerateOTP(4);
        EXPIRED = false;
        EmailVerification.onCodeSentListener = onCodeSentListener;
        switch (Server) {
            case SERVER_MIRROR:
                host = "smtp-mail.outlook.com";
                port = "587";
                username = "nhipata0@hotmail.com";
                password = "passwordis@0";
                break;
            case SERVER_MAIN:
                host = "smtp.gmail.com";
                port = "587";
                username = "emailisnull0@gmail.com";
                password = "passwordis@0";
                break;
            case SERVER_ALTERNATIVE:
                host = "smtp.elasticemail.com";
                port = "2525";
                username = "mikohe1929@art2427.com";
                password = "A278E4A657576B139A46AD89D04DACD5B946";
                break;
            case SERVER_CUSTOM:
                host = CUSTOM_HOST;
                port = CUSTOM_PORT;
                username = CUSTOM_USERNAME;
                password = CUSTOM_PASSWORD;
                break;
            default:
                throw new InvalidServerException("Server must be one of SERVER_MAIN or SERVER_MIRROR or their alternatives");
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
            message.setFrom(new InternetAddress(username,name));
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
            },1000*60*5);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            if (onCodeSentListener != null)
                onCodeSentListener.SentFailed(e);
        }
    }


    /**
     * Verify the OTP & user provided code. If user provided code is matching with OTP then, <code>VerificationSuccessful()</code> method is invoked. The OTP which is sent to the provided email address &
     * gets EXPIRED after the 5 minutes.
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
    /**
     * This will create a new server with custom properties set by you. you can use this by using <code>CUSTOM_SERVER</code>. Unfortunately, you have to declare this method every time you use CUSTOM_SERVER.
     * @param host  The host of the premium server
     * @param port  The port of the premium server
     * @param username  The username of the premium server
     * @param password  The password of the premium server
     **/
    public static void setCustomServer(String host, String port, String username, String password){
        CUSTOM_HOST = host;
        CUSTOM_PORT = port;
        CUSTOM_PASSWORD = password;
        CUSTOM_USERNAME = username;
    }
}
