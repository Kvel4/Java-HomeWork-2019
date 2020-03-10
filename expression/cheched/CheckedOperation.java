package expression.cheched;


abstract class CheckedOperation implements CheckedExpression {
    protected CheckedExpression arg1, arg2;

    public CheckedOperation(CheckedExpression arg1, CheckedExpression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Override
    public int hashCode() {
        String string = toString();
        return string.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()) {
            CheckedOperation operation = (CheckedOperation) object;
            return arg1.equals(operation.arg1) && arg2.equals(operation.arg2);
        }
        return false;
    }

    public String toString(String operand) {
        return "(" + arg1.toString() + " " + operand + " " + arg2.toString() + ")";
    }
}
