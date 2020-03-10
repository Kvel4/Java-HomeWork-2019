package expression.exceptions;

public class IllegalCharacterException extends ParseException {
    public IllegalCharacterException(String message) {
        super(message);
    }

    public IllegalCharacterException(String message, Throwable cause) {
        super(message, cause);
    }
}
