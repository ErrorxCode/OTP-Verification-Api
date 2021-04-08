package api.verification.library;

import android.security.identity.InvalidRequestMessageException;

import com.sun.mail.smtp.SMTPAddressFailedException;

import javax.mail.internet.AddressException;

public class InvalidServerException extends RuntimeException{
    public InvalidServerException(String s){
        super(s);
    }
}
class NullOTPException extends NullPointerException{
    public NullOTPException(String s){
        super(s);
    }
}

class DisposableEmailException extends AddressException {
    public DisposableEmailException(String s){
        super(s);
    }
}