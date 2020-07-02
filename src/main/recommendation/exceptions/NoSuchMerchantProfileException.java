package main.recommendation.exceptions;

public class NoSuchMerchantProfileException extends RuntimeException{
    public NoSuchMerchantProfileException() {
    }

    public NoSuchMerchantProfileException(String message) {
        super(message);
    }

    public NoSuchMerchantProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchMerchantProfileException(Throwable cause) {
        super(cause);
    }

    public NoSuchMerchantProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
