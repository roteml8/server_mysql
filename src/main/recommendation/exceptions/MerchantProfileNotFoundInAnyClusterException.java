package main.recommendation.exceptions;

public class MerchantProfileNotFoundInAnyClusterException extends RuntimeException{
    public MerchantProfileNotFoundInAnyClusterException() {
    }

    public MerchantProfileNotFoundInAnyClusterException(String message) {
        super(message);
    }

    public MerchantProfileNotFoundInAnyClusterException(String message, Throwable cause) {
        super(message, cause);
    }

    public MerchantProfileNotFoundInAnyClusterException(Throwable cause) {
        super(cause);
    }

    public MerchantProfileNotFoundInAnyClusterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
