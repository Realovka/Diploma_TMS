package by.realovka.diploma.service.exсeption;

public class NoSuchPostException extends RuntimeException {
    public NoSuchPostException() {
        super();
    }

    public NoSuchPostException(String message) {
        super(message);
    }

    public NoSuchPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPostException(Throwable cause) {
        super(cause);
    }

    protected NoSuchPostException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
