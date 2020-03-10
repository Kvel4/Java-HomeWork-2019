package expression.parser;

import expression.Const;
import expression.TripleExpression;
import expression.Variable;
import expression.cheched.*;
import expression.exceptions.*;
import org.jetbrains.annotations.Contract;


public class CheckedExpressionParse implements Parser {
    private Lexer lex;

    @Contract(pure = true)
    public CheckedExpressionParse() {
    }

    private CheckedExpression expression() throws ParseException {
        CheckedExpression prev = term();
        return expressionPrime(prev);
    }

    private CheckedExpression expressionPrime(CheckedExpression prev) throws ParseException {
        CheckedExpression term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case PLUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new CheckedAdd(prev, term));
            case MINUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new CheckedSubtract(prev, term));
            default:
                return prev;
        }
    }

    private CheckedExpression term() throws ParseException {
        CheckedExpression prev = phrase();
        return termPrime(prev);
    }

    private CheckedExpression termPrime(CheckedExpression prev) throws ParseException {
        CheckedExpression term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case MUL:
                lex.nextToken();
                term = phrase();
                return termPrime(new CheckedMultiply(prev, term));
            case DIV:
                lex.nextToken();
                term = phrase();
                return termPrime(new CheckedDivide(prev, term));
            default:
                return prev;
        }
    }

    private CheckedExpression phrase() throws ParseException {
        CheckedExpression prev = factor();
        return phrasePrime(prev);
    }

    private CheckedExpression phrasePrime(CheckedExpression prev) throws ParseException {
        CheckedExpression phrase;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case LOG:
                lex.nextToken();
                phrase = factor();
                return phrasePrime(new CheckedLog(prev, phrase));
            case POW:
                lex.nextToken();
                phrase = factor();
                return phrasePrime(new CheckedPow(prev, phrase));
            default:
                return prev;
        }
    }
    private CheckedExpression factor() throws ParseException {
        CheckedExpression tmp;
        switch (lex.getCurrentToken()) {
            case MINUS:
                lex.nextToken();
                if (lex.getCurrentToken() == Token.NUMBER) {
                    return readNumber(true);
                }
                tmp = factor();
                return new CheckedNegate(tmp);
            case NUMBER:
                return readNumber(false);
            case LPAREN:
                lex.nextToken();
                CheckedExpression shift = expression();
                if (lex.getCurrentToken() == Token.RPAREN) {
                    lex.nextToken();
                    return shift;
                }
                throw new BracketMismatchException(lex.getData() + " | " + "Expected ')' at position " + lex.getPosition());
            case VARIABLE:
                tmp = new Variable(lex.getTokenText());
                lex.nextToken();
                return tmp;
            default:
                throw new OperandMismatchException(lex.getData() + " | " +
                        "Expected number or variable or expression in brackets at position " + lex.getPosition());
        }
    }

    private CheckedExpression readNumber(boolean negative) throws ParseException {
        CheckedExpression tmp;
        try {
            if (negative) {
                tmp = new Const(Integer.parseInt("-" + lex.getTokenText()));
            } else {
                tmp = new Const(Integer.parseInt(lex.getTokenText()));
            }
        } catch (NumberFormatException e) {
            if (negative) {
                throw new OverflowException(lex.getData() + " | " + "Number at position " + (lex.getPosition()-1) + " overflowed");
            }
            throw new OverflowException(lex.getData() + " | " + "Number at position " + lex.getPosition() + " overflowed");
        }
        lex.nextToken();
        return tmp;

    }

    private void checkCorrectness() throws ParseException {
        switch (lex.getCurrentToken()) {
            case NUMBER:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected number at position " + lex.getPosition());
            case LPAREN:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected '(' at position " + lex.getPosition());
            case VARIABLE:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected variable at position " + lex.getPosition());
        }
    }

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        lex = new Lexer(expression);
        lex.nextToken();
        return expression();
    }
}
