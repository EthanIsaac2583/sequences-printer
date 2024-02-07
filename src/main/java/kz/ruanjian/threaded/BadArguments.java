package kz.ruanjian.threaded;

public class BadArguments extends RuntimeException {

    public BadArguments() {
    }

    public BadArguments(String message) {
        super(message);
    }

    public BadArguments(String message, Throwable cause) {
        super(message, cause);
    }

    public BadArguments(Throwable cause) {
        super(cause);
    }

    public BadArguments(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
