package expression.exceptions;

public class IllegalStatementException extends ParseException {
    public IllegalStatementException(String message) {
        super(message);
    }

    public IllegalStatementException(String message, Throwable cause) {
        super(message, cause);
    }
}
