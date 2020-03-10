package expression.cheched;

import expression.exceptions.FunctionRestrictionException;


public class CheckedLog extends CheckedOperation{
    public CheckedLog(CheckedExpression arg1, CheckedExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int a1 = arg1.evaluate(x, y, z), a2 = arg2.evaluate(x, y, z);
        if (a2 <= 1) {
            throw new FunctionRestrictionException("Doesnt comply with function restrictions(log base must be <= 1)");
        }
        if (a1 <= 0) {
            throw new FunctionRestrictionException("Doesnt comply with function restrictions(log numb cant be <= 0)");
        }
        return log(a1, a2);
    }

    private int log(int number, int base) {
        return (int) (Math.log(number) / Math.log(base));

    }

    @Override
    public String toString() {
        return super.toString("//");
    }
}
