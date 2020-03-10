package expression;

public class Add extends Operation {
    public Add(CustomExpression arg1, CustomExpression arg2) {
        super(arg1, arg2);
    }

    @Override
    public int evaluate(int x) {
        return arg1.evaluate(x) + arg2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return arg1.evaluate(x) + arg2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return arg1.evaluate(x, y, z) + arg2.evaluate(x, y, z);
    }

    @Override
    public String toString(){
        return '(' + arg1.toString() + " + " + arg2.toString() + ')';
    }

//    @Override
//    public String toString(){
//        return super.toString("<<");
//    }
}
