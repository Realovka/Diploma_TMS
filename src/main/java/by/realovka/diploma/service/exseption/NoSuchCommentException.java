package by.realovka.diploma.service.exseption;

public class NoSuchCommentException extends RuntimeException {
    public NoSuchCommentException() {
        super();
    }

    public NoSuchCommentException(String message) {
        super(message);
    }

    public NoSuchCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCommentException(Throwable cause) {
        super(cause);
    }

    protected NoSuchCommentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
