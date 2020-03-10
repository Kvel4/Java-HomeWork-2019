package expression;

public class ShiftLeft extends Operation {
    public ShiftLeft(CustomExpression arg1, CustomExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x) {
        return Math.toIntExact(arg1.evaluate(x) << arg2.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        // incorrect
        return 0;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return arg1.evaluate(x, y, z) << arg2.evaluate(x, y, z);
    }

    @Override
    public String toString(){
        return '(' + arg1.toString() + " << " + arg2.toString() + ')';
    }
}
