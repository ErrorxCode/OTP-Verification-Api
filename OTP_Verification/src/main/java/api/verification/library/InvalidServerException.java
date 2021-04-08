package api.verification.library;

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