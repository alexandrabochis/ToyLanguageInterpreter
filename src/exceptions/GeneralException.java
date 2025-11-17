package exceptions;

public class GeneralException extends Exception {
    public GeneralException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
