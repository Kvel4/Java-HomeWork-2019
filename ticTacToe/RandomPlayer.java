package ticTacToe;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    private int m, n;

    public RandomPlayer(final Random random, int m, int n) {
        this.m = m;
        this.n = n;
        this.random = random;
    }

    public RandomPlayer(int m, int n) {
        this(new Random(), m, n);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
