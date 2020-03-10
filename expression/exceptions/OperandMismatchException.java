package expression.exceptions;

public class OperandMismatchException extends ParseException {
    public OperandMismatchException(String message) {
        super(message);
    }

    public OperandMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
