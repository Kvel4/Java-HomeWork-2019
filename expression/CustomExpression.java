package expression;

public interface CustomExpression extends Expression, DoubleExpression, TripleExpression {
    String toString();
    int hashCode();
    boolean equals(Object object);
}
