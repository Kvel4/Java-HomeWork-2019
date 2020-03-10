package expression.exceptions;

public class OperatorMismatchException extends ParseException {
    public OperatorMismatchException(String message) {
        super(message);
    }

    public OperatorMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
