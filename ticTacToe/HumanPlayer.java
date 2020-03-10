package ticTacToe;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
//        Scanner stringScanner;
        out.println("Position");
        out.println(position);
        out.println(cell + "'s move");
        out.println("Enter row and column");
        int r, c;

        while (true) {
            r = getCoordinate();
            c = getCoordinate();
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");

//        while (true) {
//            stringScanner = new Scanner(in.nextLine());
//            try {
//                r = stringScanner.nextInt();
//                c = stringScanner.nextInt();
//                final Move move = new Move(r, c, cell);
//                if (position.isValid(move)) {
//                    return move;
//                }
//                out.println("Move " + move + " is invalid");
//            } catch (InputMismatchException e) {
//                System.out.println("Enter your move in format: integers \"row column\"");
//            }
        }
    }

    private int getCoordinate() {
        while (!in.hasNextInt()) {
            in.next();
        }
        return in.nextInt();
    }
}
