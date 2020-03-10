package expression.cheched;

import expression.exceptions.OverflowException;

public class CheckedNegate extends CheckedUnaryOperation {
    public CheckedNegate(CheckedExpression arg) {
        super(arg);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a = arg.evaluate(x, y, z);
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow while evaluating");
        }
        return -a;
    }

    @Override
    public String toString() {
        return super.toString("-");
    }
}
