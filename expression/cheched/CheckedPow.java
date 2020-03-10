package expression.cheched;

import expression.exceptions.FunctionRestrictionException;
import expression.exceptions.OverflowException;


public class CheckedPow extends CheckedOperation{
    public CheckedPow(CheckedExpression arg1, CheckedExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int numb = arg1.evaluate(x, y, z), degree = arg2.evaluate(x, y, z);
        if (degree < 0 || numb == 0 && degree == 0) {
            throw new FunctionRestrictionException("Doesnt comply with function restrictions");
        }

        int ans = 1;
        while (degree != 0) {
            if (degree % 2 == 1){
                degree--;
                checkMultiply(ans, numb);
                ans *= numb;
            }
            if (degree != 0) {
                checkMultiply(numb, numb);
                numb *= numb;
                degree /= 2;
            }
        }
        return ans;
    }


    private void checkMultiply(int a1, int a2) {
        int r = a1 * a2;
        int ax = Math.abs(a1);
        int ay = Math.abs(a2);
        if (((ax | ay) >>> 15 != 0)) {
            if (((a2 != 0) && (r / a2 != a1)) ||
                    (a1 == Integer.MIN_VALUE && a2 == -1)) {
                throw new OverflowException("Overflow while evaluating");
            }
        }
    }

    @Override
    public String toString() {
        return super.toString("**");
    }
}
