package expression;


import expression.cheched.CheckedExpression;

public class Const implements CustomExpression, CheckedExpression {
    private Number val;

    public Const(Number val) {
        this.val = val;
    }

    @Override
    public int evaluate(int x) {
        return val.intValue();
    }

    @Override
    public double evaluate(double x) {
        return val.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val.intValue();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()) {
            Const var = (Const) object;
            return val.equals(var.val);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
