package ticTacToe;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board extends Cloneable {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
    Board clone();
}
