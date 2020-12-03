package by.realovka.diploma.service.exсeption;

public class UserIsDeletedException extends RuntimeException {
    public UserIsDeletedException() {
        super();
    }

    public UserIsDeletedException(String message) {
        super(message);
    }

    public UserIsDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsDeletedException(Throwable cause) {
        super(cause);
    }

    protected UserIsDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
