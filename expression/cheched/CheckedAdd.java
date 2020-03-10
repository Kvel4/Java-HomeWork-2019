package expression.cheched;

import expression.exceptions.OverflowException;


public class CheckedAdd extends CheckedOperation {
    public CheckedAdd(CheckedExpression arg1, CheckedExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a1 = arg1.evaluate(x, y, z), a2 = arg2.evaluate(x, y, z);
        if ((a2 > 0) && (a1 > Integer.MAX_VALUE - a2)) {
            throw new OverflowException("Overflow while evaluating");
        }else if ((a2 < 0) && (a1 < Integer.MIN_VALUE - a2)) {
            throw new OverflowException("Overflow while evaluating");
        }
        return a1 + a2;
    }

    @Override
    public String toString(){
        return super.toString("+");
    }
}
