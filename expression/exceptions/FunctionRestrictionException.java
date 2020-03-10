package expression.exceptions;

public class FunctionRestrictionException extends EvaluateException {
    public FunctionRestrictionException(String message) {
        super(message);
    }

    public FunctionRestrictionException(String message, Throwable cause) {
        super(message, cause);
    }
}
