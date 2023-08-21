package epam.com.esm.exception.types;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException(String message) {
        super(message);
    }
}