package expression.parser;

import expression.*;
import expression.exceptions.IllegalStatementException;
import expression.exceptions.ParseException;
import org.jetbrains.annotations.Contract;


public class ExpressionParser implements Parser {
    private Lexer lex;

    @Contract(pure = true)
    public ExpressionParser() {
    }

    private CustomExpression shift() throws ParseException {
        CustomExpression prev = expression();
        return shiftPrime(prev);
    }

    private CustomExpression shiftPrime(CustomExpression prev) throws ParseException {
        CustomExpression exp;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case SHIFT_LEFT:
                lex.nextToken();
                exp = expression();
                return shiftPrime(new ShiftLeft(prev, exp));
            case SHIFT_RIGHT:
                lex.nextToken();
                exp = expression();
                return shiftPrime(new ShiftRight(prev, exp));
            case NUMBER:
                throw new IllegalStatementException("Unexpected number at position " + lex.getPosition());
            case LPAREN:
                throw new IllegalStatementException("Unexpected '(' at position " + lex.getPosition());
            case VARIABLE:
                throw new IllegalStatementException("Unexpected variable at position " + lex.getPosition());
            default:
                return prev;
        }
    }

    private CustomExpression expression() throws ParseException{
        CustomExpression prev = term();
        return expressionPrime(prev);
    }

    private CustomExpression expressionPrime(CustomExpression prev) throws ParseException{
        CustomExpression term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case PLUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new Add(prev, term));
            case MINUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new Subtract(prev, term));
            default:
                return prev;
        }
    }

    private CustomExpression term() throws ParseException {
        CustomExpression prev = factor();
        return termPrime(prev);
    }

    private CustomExpression termPrime(CustomExpression prev) throws ParseException {
        CustomExpression term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case MUL:
                lex.nextToken();
                term = factor();
                return termPrime(new Multiply(prev, term));
            case DIV:
                lex.nextToken();
                term = factor();
                return termPrime(new Divide(prev, term));
            default:
                return prev;
        }
    }

    private CustomExpression factor() throws ParseException {
        CustomExpression tmp;
        switch (lex.getCurrentToken()) {
            case MINUS:
                lex.nextToken();
                if (lex.getCurrentToken() == Token.NUMBER) {
                    return readNumber(true);
                }
                tmp = factor();
                return new Subtract(new Const(0), tmp);
            case NUMBER:
                return readNumber(false);
            case LPAREN:
                lex.nextToken();
                CustomExpression shift = shift();
                if (lex.getCurrentToken() == Token.RPAREN) {
                    lex.nextToken();
                    return shift;
                }
                throw new IllegalStatementException("Expected ')' at position " + lex.getPosition());
            case VARIABLE:
                tmp = new Variable(lex.getTokenText());
                lex.nextToken();
                return tmp;
            default:
                throw new IllegalStatementException(
                        "Expected number or variable or expression in brackets at position " + lex.getPosition());
        }
    }

    private CustomExpression readNumber(boolean negative)throws ParseException {
        CustomExpression tmp;
        try {
            if (negative) {
                tmp = new Const(Integer.parseInt("-" + lex.getTokenText()));
            } else {
                tmp = new Const(Integer.parseInt(lex.getTokenText()));
            }
        } catch (NumberFormatException e) {
            throw new IllegalStatementException("Number at position " + lex.getPosition() + " overflowed", e);
        }
        lex.nextToken();
        return tmp;

    }

    private void checkCorrectness()throws ParseException {
        switch (lex.getCurrentToken()) {
            case NUMBER:
                throw new IllegalStatementException("Unexpected number at position " + lex.getPosition());
            case LPAREN:
                throw new IllegalStatementException("Unexpected '(' at position " + lex.getPosition());
            case VARIABLE:
                throw new IllegalStatementException("Unexpected variable at position " + lex.getPosition());
        }
    }

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        lex = new Lexer(expression);
        lex.nextToken();
        return shift();
    }
}
