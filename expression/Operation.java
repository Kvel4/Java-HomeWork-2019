package expression;

abstract class Operation implements CustomExpression {
    protected CustomExpression arg1, arg2;

    public Operation(CustomExpression arg1, CustomExpression arg2) {
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
            Operation operation = (Operation) object;
            return arg1.equals(operation.arg1) && arg2.equals(operation.arg2);
        }
        return false;
    }
    
//    public String toString(String operand) {
//        return "(" + arg1.toString() + " " + operand + " " + arg2.toString() + ")";
//    }
}
