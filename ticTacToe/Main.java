package ticTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        final SimpleGame game = new SimpleGame(false, new HumanPlayer(), new HumanPlayer());
        Scanner in = new Scanner(System.in);
        Scanner stringScanner;
        int result, m, n, k;
        while (true) {
            System.out.println("Enter yours board parameters: m, n, k");
            stringScanner = new Scanner(in.nextLine());
            try {
                m = stringScanner.nextInt();
                n = stringScanner.nextInt();
                k = stringScanner.nextInt();
                if (m <= 0 || n <= 0 || k <= 0) {
                    System.out.println("Arguments must be >= 1");
                    continue;
                }
                if (k > Math.max(m, n)) {
                    System.out.println("Result always will be DRAW");
                    continue;
                }
                break;
            } catch (InputMismatchException ignored) {
                System.out.println("Parameters must be Integers");
            }
        }
        result = game.play(new MnkBoard(m, n, k));
        System.out.println("Gamer number " + result + " win");
    }
}
