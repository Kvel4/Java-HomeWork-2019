package expression.exceptions;

public class BracketMismatchException extends ParseException {
    public BracketMismatchException(String message) {
        super(message);
    }

    public BracketMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
