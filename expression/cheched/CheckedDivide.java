package expression.cheched;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluateException;
import expression.exceptions.OverflowException;

public class CheckedDivide extends CheckedOperation {
    public CheckedDivide(CheckedExpression arg1, CheckedExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) throws EvaluateException {
        int a1 = arg1.evaluate(x, y, z), a2 = arg2.evaluate(x, y, z);
        if ((a2 == -1) && (a1 == Integer.MIN_VALUE)) {
            throw new OverflowException("Overflow while evaluating");
        }
        if (a2 == 0) {
            throw new DivisionByZeroException("Division by zero while evaluating");
        }
        return a1 / a2;
    }

    @Override
    public String toString(){
        return super.toString("/");
    }
}
