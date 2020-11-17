package by.realovka.diploma.service.exseption;

public class SuchUserIsPresentAlreadyException extends RuntimeException{
    public SuchUserIsPresentAlreadyException() {
        super();
    }

    public SuchUserIsPresentAlreadyException(String message) {
        super(message);
    }

    public SuchUserIsPresentAlreadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SuchUserIsPresentAlreadyException(Throwable cause) {
        super(cause);
    }

    protected SuchUserIsPresentAlreadyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
