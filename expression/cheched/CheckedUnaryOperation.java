package expression.cheched;

abstract class CheckedUnaryOperation implements CheckedExpression {
    protected CheckedExpression arg;

    public CheckedUnaryOperation(CheckedExpression arg) {
        this.arg = arg;
    }

    @Override
    public int hashCode() {
        String string = toString();
        return string.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()) {
            CheckedUnaryOperation operation = (CheckedUnaryOperation) object;
            return arg.equals(operation.arg);
        }
        return false;
    }

    public String toString(String operand) {
        return operand + "(" + arg.toString() + ")";
    }
}
