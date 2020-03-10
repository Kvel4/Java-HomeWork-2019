package expression.cheched;

import expression.TripleExpression;

public interface CheckedExpression extends TripleExpression {
    String toString();
    int hashCode();
    boolean equals(Object object);
}
