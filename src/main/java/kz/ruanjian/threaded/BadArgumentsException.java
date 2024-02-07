package kz.ruanjian.threaded;

public class BadArgumentsException extends RuntimeException {

    public BadArgumentsException() {
    }

    public BadArgumentsException(String message) {
        super(message);
    }

    public BadArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadArgumentsException(Throwable cause) {
        super(cause);
    }

    public BadArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
