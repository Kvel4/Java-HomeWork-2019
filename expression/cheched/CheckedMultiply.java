package expression.cheched;

import expression.exceptions.OverflowException;

public class CheckedMultiply extends CheckedOperation {
    public CheckedMultiply(CheckedExpression arg1, CheckedExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {

        int a1 = arg1.evaluate(x, y, z), a2 = arg2.evaluate(x, y, z);

        int r = a1 * a2;
        int ax = Math.abs(a1);
        int ay = Math.abs(a2);
        if (((ax | ay) >>> 15 != 0)) {
            if (((a2 != 0) && (r / a2 != a1)) ||
                    (a1 == Integer.MIN_VALUE && a2 == -1)) {
                throw new OverflowException("Overflow while evaluating");
            }
        }
        return r;
    }

    @Override
    public String toString(){
        return super.toString("*");
    }
}
