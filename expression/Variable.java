package expression;


import expression.cheched.CheckedExpression;

public class Variable implements CustomExpression, CheckedExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return (x);
            case "y":
                return (y);
            case "z":
                return (z);
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && getClass() == object.getClass()) {
            Variable var = (Variable) object;
            return name.equals(var.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
