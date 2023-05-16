package live.stoicism.productsampleapi.exception;

public class DatabaseInvalidException extends RuntimeException {
    public DatabaseInvalidException(String message) {
        super(message);
    }

    public DatabaseInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
